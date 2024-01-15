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

    request(url, options) {
        let token = FetchInterceptor.theInstance.session.currentToken;
        //console.log("FetchInterceptor request: ", url, options, token);

        if (token == null) {
            return [url, options];
        } else if (options == null) {
            return [url, { headers: { Authorization: token }}]
        } else {
            let newOptions = { ...options };
            // TODO combine existing headers with new Authorization header
            newOptions.headers = {
                ...options.headers,
                Authorization: token,
            }
            // console.log("FetchInterceptor request: ", url, newOptions);
            return [url, newOptions];
        }
    }
    requestError(error) {
        // Called when an error occured during another 'request' interceptor call
        return Promise.reject(error);
    }
    response(response) {
        // Modify the reponse object
        // console.log("FetchInterceptor response: ", response);
        if (response.status >= 400 && response.status < 600) {
            FetchInterceptor.theInstance.handleErrorInResponse(response);
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

    async handleErrorInResponse(response) {
        if (response.status == 401) {
            console.log(response);
            this.router.push({ name: 'SIGN-IN',
                params: { welcomeMessage: "Your token has expired, you need to logon again",
                }});   // vue-router
        } else if (response.status != 406) {
            let errorData = await response.json();
            console.log("FetchInterceptor response error data: ", errorData);
            let errorMessage = `Request-url = ${response.request.url}`
                + `<br>Response status code = ${response.status}`
                + `<br>Error Message = ${errorData.error}: ${errorData.message}`
            this.router.push({ name: 'ERROR', params: { message: errorMessage}});  // vue-router
        }
    }

}

