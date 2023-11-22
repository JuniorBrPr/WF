import {Cabin} from "@/models/cabin";

export class CabinsAdaptor {
    resourceUrl;

    constructor(resourceUrl) {
        this.resourceUrl = resourceUrl;
        console.log("Created CabinsAdaptor for " + resourceUrl);
    }

    async fetchJson(url, options = null) {
        let response = await fetch(url, options);
        if (response.ok) {
            return await response.json();
        } else {
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null;
        }
    }

    async asyncFindAll() {
        console.log("CabinsAdaptor.findAll() called");
        console.log(this.resourceUrl)
        const cabins = await this.fetchJson(this.resourceUrl);
        return cabins?.map(Cabin.copyConstructor);
    }

    async asyncFindById(id) {
        console.log("CabinsAdaptor.findById() called");
        const cabin = await this.fetchJson(this.resourceUrl + id);
        return Cabin.copyConstructor(cabin);
    }

    async asyncSave(cabin) {


        if (cabin.id === 0) {
            const response = await this.fetchJson(this.resourceUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(cabin)
            })

            if (response && response.id) {
                cabin.id = response.id
            }
            return cabin;
        } else {
            return await this.fetchJson(`${this.resourceUrl}/${cabin.id}`, {
            method: 'PUT',
                headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cabin)
        })
    }

}

    async asyncDeleteById(id) {
        console.log("CabinsAdaptor.deleteById() called");
        const options = {
            method: "DELETE",
            headers: {"Content-Type": "application/json"},
        }
        const response = await this.fetchJson(this.resourceUrl + id, options);
        if (response.ok) {
            return Cabin.copyConstructor(await response.json());
        } else {
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null;
        }
    }
}