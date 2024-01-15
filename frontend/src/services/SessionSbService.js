export class SessionSbService {
    constructor(resourcesUrl, browserStorageItemName) {
        console.log("Created SessionService...");
        this.BROWSER_STORAGE_ITEM_NAME = browserStorageItemName;
        this.RESOURCES_URL = resourcesUrl;
        this._currentAccount = null;
        this._currentToken = null;
        // Retrieve the current user info from browser storage,
        // e.g., after a page reload or when a new tab is opened.
        this.getTokenFromBrowserStorage();
    }


    get currentAccount() {
        return this._currentAccount;
    }

    get currentToken() {
        return this._currentToken;
    }

    isAuthenticated() {
        return this._currentAccount != null;
    }

    getTokenFromBrowserStorage() {
        if (this._currentToken != null) return this._currentToken;
        this._currentToken = window.sessionStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME);
        const jsonAccount = window.sessionStorage.getItem(`${this.BROWSER_STORAGE_ITEM_NAME}_ACC`);

        if (jsonAccount != null) {
            this._currentAccount = JSON.parse(jsonAccount);
        }
        console.log("SessionService recovered token: ", this._currentToken);
        console.log("Current Account:", this._currentAccount);
        return this._currentToken;
    }

    saveTokenIntoBrowserStorage(token, account) {
        this._currentToken = token;
        this._currentAccount = account;
        if (token == null) {
            this._currentAccount = null;
            window.sessionStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
            window.sessionStorage.removeItem(`${this.BROWSER_STORAGE_ITEM_NAME}_ACC`);
        } else {
            console.log(`New token for ${account.name}: ${token}`);
            window.sessionStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME, token);
            window.sessionStorage.setItem(`${this.BROWSER_STORAGE_ITEM_NAME}_ACC`, JSON.stringify(account));
        }
    }

    async asyncSignIn(email, password) {
        try {
            const response = await fetch(`http://localhost:8086/api/authentication/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({email: email, password: password}),
            });

            if (response.ok) {
                const token = response.headers.get('Authorization');
                console.log("Token: ", token);
                // const user = await response.json();
                // this.saveTokenIntoBrowserStorage(token, user);
                // return  user;
            } else {
                console.log(response);
                return null;
            }
        } catch (error) {
            console.error('RequestError occurred during sign-in:', error);
            return null;
        }
    }

    signOut() {
        this.saveTokenIntoBrowserStorage(null, null);
    }
}
