export class SessionSbService {
    BROWSER_STORAGE_ITEM_NAME;
    RESOURCES_URL;
    currentAccount;
    constructor(resourcesUrl, browserStorageItemName) {
        console.log("Created SessionService...");
        this.BROWSER_STORAGE_ITEM_NAME = browserStorageItemName;
        this.RESOURCES_URL = resourcesUrl;
        console.log(this.RESOURCES_URL)
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
        // console.log("SessionService recovered token: ", this._currentToken);
        // console.log("Current Account:", this._currentAccount);
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
        const body = JSON.stringify({ email: email, password: password });
        try {
            let response = await fetch(this.RESOURCES_URL + '/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: body,
                credentials: "include"
            });

            if (response.ok) {
                let account = await response.json();
                const token = response.headers.get('Authorization');
                this.saveTokenIntoBrowserStorage(token, account);
                return { account, token }; // Return both account and token
            } else {
                console.log(response);
                console.log(this.RESOURCES_URL)
                return null;
            }
        } catch (error) {
            console.error('Error occurred during sign-in:', error);
            console.log(this.RESOURCES_URL)
            return null;
        }
    }

    signOut() {
        this.saveTokenIntoBrowserStorage(null, null);
    }
}
