const guessTheNumber = (function() {
    let num = '';
    let count = 0;
    let digits = 0;

    function generateNum(numDigits) { // generate random four-digit number for guess
        digits = numDigits;
        const randArr = new Array(numDigits);
        let randNum = 0;

        for (let i = 0; i < randArr.length; i++) {
            while(true) { // while element dont dup
                randNum = Math.floor(Math.random() * 10);
                if (!randArr.includes(randNum)) break;
            }
            randArr[i] = randNum;
        }
        console.log(randArr.join(''));
        return randArr.join('');
    }

    function guessing(guessNum) {
        if (num === guessNum) {
            alert(num + ' : CORRECT! - ' + (count + 1) + ' tried');
            document.getElementById('guessNumber').setAttribute('readonly', true);
            return true;
        } else {
            count++;
            let strike = 0;
            let ball = 0;

            [...guessNum].forEach(function(item, idx) {
                if (num.includes(item)) {
                    if (num.indexOf(item) === idx) strike++;
                    else ball++;
                }
            });

            const resultHtml = document.createElement('p');
            resultHtml.append(guessNum + ' : ');

            if (strike + ball === 0) {
                alert('OUT.');
                resultHtml.append('OUT');
            } else if (strike !== 0 && ball !== 0) {
                alert(strike + ' STRIKE, ' + ball + ' BALL');
                resultHtml.append(strike + ' STRIKE, ' + ball + ' BALL');
            } else if (strike !== 0) {
                alert(strike + ' STRIKE');
                resultHtml.append(strike + ' STRIKE, ');
            } else {
                alert(ball + ' BALL');
                resultHtml.append(ball + ' BALL');
            }

            document.getElementById('record').appendChild(resultHtml);

            return false
        }
    }

    function cleanRecord() {
        document.getElementById('record').innerHTML = '';
    }

    return {
        start(numDigits) {
            count = 0;
            num = generateNum(numDigits);
            cleanRecord();
            document.getElementById('guessNumber').removeAttribute('readonly');
        },
        guess(guessNum) {
            return guessing(guessNum);
        },
        getCount() {
            return count;
        },
        getDigits() {
            return digits;
        }
    };
}());

document.getElementById('guessNumber').setAttribute('readonly', true);

const startBtn = document.getElementById('start');
const guessBtn = document.getElementById('guess');

startBtn.addEventListener('click', function() {
    let digits = 0;
    try {
        digits = new Number(prompt("DIGITS OF NUMBER"));
    } catch {
        alert('PLZ TYPE 0 ~ 9 NUMBER');
        return;
    }

    if (!digits || digits < 1 || digits > 9) {
        alert('PLZ TYPE 0 ~ 9 NUMBER');
        return;
    }
    guessTheNumber.start(digits);
});

guessBtn.addEventListener('click', function() {
    if (document.getElementById('guessNumber').hasAttribute('readonly') === true) {
        console.log('hi?');
        return;
    }

    let tmp = String(document.getElementById('guessNumber').value);
    let tmpArr = [...tmp];
    let arrLength = tmpArr.length;

    if (!tmp || arrLength != guessTheNumber.getDigits()) {
        alert('PLZ TYPE ONLY ' + guessTheNumber.getDigits() + '-digit NUNBER');
        return;
    }

    let poped = '';
    for (let i = 0; i < arrLength; i++) {
        // last element for check dup arr
        poped = tmpArr.pop();
        if (tmpArr.includes(poped)) {
            alert('REMOVE DUP NUMBER');
            return;
        }
    }

    guessTheNumber.guess(tmp);
});