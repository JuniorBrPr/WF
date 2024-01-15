import fetchIntercept from 'fetch-intercept';

export class FetchInterceptor {
    static theInstance; // the singleton instance that has been registered
    session;
    router;
    unregister; // callback function to unregister this instance at shutdown
    //static getToken;
    //static router;
    constructor(session, router) {
        FetchInterceptor.theInstance = this;
        this.session = session;
        this.router = router;
        // fetchIntercept does not register the object closure, only the methods as functions
        this.unregister = fetchIntercept.register(this);

        // javascript does not know session::getCurrentToken
        //FetchInterceptor.getToken = () => session.getCurrentToken();
        //FetchInterceptor.router = router;
        console.log("FetchInterceptor has been registered; current token = ",
            FetchInterceptor.theInstance.session.currentToken );
    }

    request = (url, options) => {
        let token = this.session.currentToken;

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

    requestError = (error) => {
        console.error('Request error:', error);
        return Promise.reject(error);
    };

    response = (response) => {
        if (response.status === 401) {
            this.router.push('/sign-out');
        }
        return response;
    }
    responseError(error) {
        // Handle a fetch error
        console.log("FetchInterceptor responseError: ", error);
        let errorMessage = "Some issues with server connectivity?<br/>" + error
        // FetchInterceptor.router.navigate(['/error'], { state: { message: errorMessage}});  // ng-router
        FetchInterceptor.theInstance.router.push({ name: 'ERROR', params: { message: errorMessage}});  // vue-router
        return Promise.reject(error);
    }

    unregisterInterceptor() {
        this.unregister();
    }
}
