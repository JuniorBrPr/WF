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
        const cabins = await this.fetchJson(this.resourceUrl);
        return cabins?.map(s => Cabin.copyConstructor(s));
    }

    async asyncFindById(id){
        console.log("CabinsAdaptor.findById() called");
        const cabin = await this.fetchJson(this.resourceUrl + id);
        return Cabin.copyConstructor(await cabin);
    }

    async asyncSave(cabin){
        console.log("CabinsAdaptor.save() called");
        const options = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(cabin)
        }

        let response = await this.fetchJson(this.resourceUrl, options);

        if (response.status === 409) {
            options.method = "PUT";
            response = await this.fetchJson(this.resourceUrl + cabin.id, options);
        }

        if (response.ok) {
            return await response.json();
        } else {
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null;
        }
    }

    async asyncDeleteById(id){
        console.log("CabinsAdaptor.deleteById() called");
        const options = {
            method: "DELETE",
            headers: {"Content-Type": "application/json"},
        }
        const response = await this.fetchJson(this.resourceUrl + id, options);
        if (response.ok) {
            return await response.json();
        } else {
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null;
        }
    }
}