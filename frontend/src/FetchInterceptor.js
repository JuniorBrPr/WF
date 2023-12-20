import fetchIntercept from 'fetch-intercept';

export class FetchInterceptor {
    static theInstance;
    session;
    router;
    unregister;

    constructor(session, router) {
        this.session = session;
        this.router = router;

        FetchInterceptor.theInstance = this;
        FetchInterceptor.theInstance = this;
        this.request = this.request.bind(this);
        this.requestError = this.requestError.bind(this);
        this.response = this.response.bind(this);
        this.responseError = this.responseError.bind(this);


        this.unregister = fetchIntercept.register({
            request: this.request,
            requestError: this.requestError,
            response: this.response,
            responseError: this.responseError,
        });

        console.log("FetchInterceptor has been registered. Current token = " + this.session.currentToken);
    }

    request(url, options) {
        let token = this.session.getTokenFromBrowserStorage();
        console.log(token.toString());
        if (token === null) {
            return [url, options];
        } else if (options === null || !options.headers) {
            return [url, {headers: {Authorization: token}}];
        } else {
            let newOptions = {...options};
            newOptions.headers = {
                ...newOptions.headers, Authorization: token,
            };
            return [url, newOptions];
        }
    }

    requestError(error) {
        console.error('Request error:', error);
        return Promise.reject(error);
    }

    response(response) {
        console.log("Response: ", response);
        if (response.status.toString().charAt(0) === '4') {
            return this.responseError(response);
        }
        return response;
    }

    responseError(response) {
        if (!this.session.isAuthenticated) {
            this.router.push('/sign-out');
            return response;
        }
        if (response.status === 401) {
            this.router.push({
                name: 'Error', params: {
                    status: response.status,
                    statusText: response.statusText,
                    message: "You are not authorized to access this resource.",
                }
            });
            return response;
        }
        this.router.push({
            name: 'Error', params: {
                status: response.status,
                statusText: response.statusText,
                message: "An error occurred while accessing the resource.",
            }
        });
        return response;
    }
}
