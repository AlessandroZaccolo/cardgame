
// --- Call the functions you want to try ---
//basics();
//functions();
//arrays();
//objects();
//classes();
dom();
//events();

/** Basics: variables, if, while, for */
function basics() {

    // var message = "hello js"; // var is the old keyword
    let name = "Ferran";
    let school = "BTS";

    console.log("I'm " + name + " and I'm in " + school);
    console.log(`I'm ${name} and I'm in ${school}`);

    let age = 20;

    if (age >= 18) {
        console.log("adult");
    } else {
        console.log("not adult");
    }

    let i = 1;
    while (i <= 5) {
        console.log(i);
        i++;
    }

    for (let j = 1; j <= 5; j++) {
        console.log(j);
    }
}

function functions(){
    let result = sum(4, 6);
    console.log("The result is " + result);

    greet("BTS class")

    function greet(name) {
        console.log("hello "+ name);
    }

    function sum(x, y) {
        return x + y;
    }
}

/** Arrays, creating, adding (push), looping */
function arrays() {

    let array = [2, 4, 6];
    let array2 = [ ];

    array.push(8);
    array.push(10);

    let x = array[1];

    console.log("x = " + x);
    console.log(array);
    console.log("array = " + array);

    for (let i = 0; i < array.length; i++) {
        console.log("Value at index " + i + " is " + array[i]);
    }

    for (let value of array) {
        console.log(value);
    }
}

/** Arrays, creating, adding (push), looping */
function arrays() {

    let array = [2, 4, 6];
    let array2 = [ ];

    array.push(8);
    array.push(10);

    let x = array[1];

    console.log("x = " + x);
    console.log(array);
    console.log("array = " + array);

    for (let i = 0; i < array.length; i++) {
        console.log("Value at index " + i + " is " + array[i]);
    }

    for (let value of array) {
        console.log(value);
    }
}

/** Classes, constructor, methods, creating objects */
function classes() {

    class Car {

        // You don't declare the fields

        constructor(name, maxSpeed) {
            this.name = name;
            this.maxSpeed = maxSpeed;
            this.speed = 0;
        }

        accelerate(amount) {
            this.speed += amount;
        }
    }

    let car = new Car("Audi", 250);
    car.accelerate(50);
    car.accelerate(20);

    // This is a simple object, not created from class
    let car2 = {
        name: "Opel",
        speed: 100
    };

    console.log("Now the " + car.name + " is running at " + car.speed);
    console.log("Now the " + car2.name + " is running at " + car2.speed);

    console.log(car);
    console.log(car2);
}




let elem = document.getElementById("text");
console.log(elem);
console.log("The content of the element is: "+ elem.textContent);
elem.textContent = "This is another text";


function events(){

    // Create a button
    let button = document.createElement("button");
    button.textContent = "Display products";

    // Listen to clicks on the button
    button.onclick = function () {
        console.log("Button was clicked!");
        displayProducts();
    };

    // Add the button to the web page
    let container = document.getElementById("container");
    container.appendChild(button);

    function displayProducts(){

        // Getting an element from the HTML
        let container = document.getElementById("container");

        let array = [{name: "Iphone", price: 1200, available: true}, { name: "Pixel", price: 900, available: false}];

        let div = document.createElement("div");

        for (let value of array) {
            // Create a new element (paragraph)
            let paragraph = document.createElement("p");
            // Change the content of the paragraph
            paragraph.textContent = value.name + " costs " + value.price;
            // Add the paragraph to the container (so it is displayed)
            div.appendChild(paragraph);
        }

        container.appendChild(div);


    }


}




