
exports.createArrOfInts =  function (length) {
    array = [];
    for (let i = 0; i < length; ++i) {
        array.push(i);
    }
    return array;
}

exports.shuffleArrOfInts = function (arr) {

    // Start from the last element and
    // swap one by one. We don't need to
    // run for the first element
    // that's why i > 0
    for (let i = arr.length - 1; i > 0; i--) {

        // Pick a random index from 0 to i inclusive
        let rand_index = Math.floor(Math.random() * (i + 1));

        // Swap arr[i] with the element
        // at random index
        [arr[i], arr[rand_index]] = [arr[rand_index], arr[i]];

    }
}