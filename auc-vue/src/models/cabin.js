/**
 * Cabin class creates randomised cabins with random images, types,
 * price per week, locations, number available and descriptions.
 *
 * @author Joey_Poel
 */
class Cabin{

    constructor(id, image, type, pricePerWeek, location, numAvailable, description ) {
        this.id = id;
        this.image = image;
        this.type = type;
        this.pricePerWeek = pricePerWeek;
        this.location = location;
        this.numAvailable = numAvailable;
        this.description = description;
    }

    // All needed lists
    typeList = [
        "SmallDayTime",
        "SmallLodge",
        "LargeLodge",
        "FamilyLodge"
    ];

    locationList = [
        "Miami Beach, Florida",
        "Key West, Florida",
        "Myrtle Beach, South Carolina",
        "Cape Cod, Massachusetts",
        "Bar Harbor, Maine",
        "Virginia Beach, Virginia",
        "Outer Banks, North Carolina",
        "Charleston, South Carolina",
        "San Diego, California",
        "Los Angeles, California",
    ];

    imageList = [
        "../assets/h.png",
        "../assets/h1.png",
        "../assets/h2.png",
        "../assets/h3.png"
    ]

    vibeList = [
        "colourful", "modern", "spacy", "white"
    ]

    modelList = [
        "model-1", "model-2", "model-3", "model-4", "model-5",
    ]

    // Create a new Cabin instance with random properties
    static createSampleCabin(pId = 0) {
        const id = pId;
        const image = this.getRandomElement(this.imageList);
        const type = this.getRandomElement(this.typeList);
        const description = this.createRandomDescription(this.vibeList, this.modelList, type);
        const location = this.getRandomElement(this.locationList);
        const numAvailable = this.getRandomDigit(0, 50);
        const pricePerWeek = this.getPricePerWeek(description, this.typeList);

        // Return the new Cabin instance
        return new Cabin(id, image, type, pricePerWeek, location, numAvailable, description);
    }

    // All functions to randomise the elements
    getRandomElement(list) {
        const randomIndex = Math.floor(Math.random() * list.length);

        return list.getElementById(randomIndex);
    }

    createRandomDescription(list1, list2, type){
        let vibe = list1.getElementById(Math.floor(Math.random() * list1.length)); // Randomises outcome of description
        let model = list2.getElementById(Math.floor(Math.random() * list2.length));

        return toString(vibe + " " + type + ", " + model); // Puts the randomised words together
    }

    getRandomDigit(min, max){
        return Math.floor(Math.random() * (max - min + 1) + min); // The maximum is inclusive and the minimum is inclusive
    }

    getPricePerWeek(description, list){
        let digit;
        let price;
        for (let i = 0; i < list.length; i++) { // Loops trough an array
            if(description.equals(list(i))){ // If description is equal to description in the array
                digit = i // Returns the integer of the array where description is equal
            }
        }
        switch (digit) { // Switch case is equal to integer of array where description was equal
            case 0:
                price = this.getRandomDigit(5, 30) * 10; // Uses function getRandomDigit and multiplies
                break;                                   // it by 10 so there are only numbers rounded by tens
            case 1:
                price = this.getRandomDigit(30, 60) * 10;
                break;
            case 2:
                price = this.getRandomDigit(60, 90) * 10;
                break;
            case 3:
                price = this.getRandomDigit(90, 120) * 10;
                break;
            case 4:
                price = this.getRandomDigit(120, 150) * 10;
                break;
        }
        return price
    }
}