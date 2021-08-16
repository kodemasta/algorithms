var utils = require( '../utils.js');

function test1() {
    let arr = utils.createArrOfInts(10);
    console.log(arr.toString());

    utils.shuffleArrOfInts(arr);
    console.log(arr.toString());
}

test1();



