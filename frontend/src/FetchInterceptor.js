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

        this.unregister = fetchIntercept.register({
            request: this.request,
            requestError: this.requestError,
            response: this.response,
            responseError: this.responseError,
        });

        console.log(
            "FetchInterceptor has been registered. Current token = " +
null        );
    }

    request(url, options) {
        let token = null;

        if (token === null) {
            return [url, options];
        } else if (options === null || !options.headers) {
            return [url, { headers: { Authorization: token } }];
        } else {
            let newOptions = { ...options };
            newOptions.headers = {
                ...newOptions.headers,
                Authorization: token,
            };
            return [url, newOptions];
        }
    }

    requestError(error) {
        console.error('Request error:', error);
        return Promise.reject(error);
    }

    response(response) {
        return response;
    }

    responseError(error) {
        console.error('Response error:', error);
        return Promise.reject(error);
    }

    unregisterInterceptor() {
        this.unregister();
    }
}
