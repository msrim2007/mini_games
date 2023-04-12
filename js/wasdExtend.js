const $UP = document.querySelector('.UP');
const $LEFT = document.querySelector('.LEFT');
const $DOWN = document.querySelector('.DOWN');
const $RIGHT = document.querySelector('.RIGHT');
const $msg = document.querySelector('.msg');
const $header = document.querySelector('.start');
const $arrowList = document.querySelector('.list');
const ARROW = ['UP', 'LEFT', 'DOWN', 'RIGHT'];
const ARROWEMOJI = ['&#8679;', '&#8678;', '&#8681;', '&#8680;'];

class WASD {
    #startDate;
    #endDate;
    #state = 'stop';
    #gameArr = [];
    #arrNum = -1;

    getState() {
        return this.#state;
    }

    startWasd() {
        this.#state = 'start';
        $header.textContent = 'START';
        for (let i = 0; i < 30; i++) {
            this.#gameArr.push(Math.floor(Math.random() * 4));
            $arrowList.appendChild(document.createElement('li')).innerHTML = ARROWEMOJI[this.#gameArr[i]];
            if (i > 10) {
                $arrowList.lastChild.style.display = 'none';
            }
        }
        this.#arrNum = -1;
        this.nextArrow();
        this.#startDate = new Date();
    }

    checkArrow(arrow) {
        if (arrow == ARROW[this.#gameArr[this.#arrNum]]) {
            $arrowList.removeChild($arrowList.firstElementChild);
            if ($arrowList.childNodes.length > 11) {
                $arrowList.childNodes[11].style.display = 'block';
            }
            this.nextArrow();
        }
    }

    nextArrow() {
        if (this.#arrNum === this.#gameArr.length - 1) {
            this.#endDate = new Date();
            this.gameOver();
        } else {
            this.#arrNum++;
            if (ARROW[this.#gameArr[this.#arrNum]] == 'UP') {
                $UP.style['background-color'] = 'chartreuse';
            } else if (ARROW[this.#gameArr[this.#arrNum]] == 'LEFT') {
                $LEFT.style['background-color'] = 'chartreuse';
            } else if (ARROW[this.#gameArr[this.#arrNum]] == 'DOWN') {
                $DOWN.style['background-color'] = 'chartreuse';
            } else {
                $RIGHT.style['background-color'] = 'chartreuse';
            }
        }
    }

    gameOver() {
        this.#state = 'stop';
        this.#gameArr = [];
        $header.textContent = '\'SPACE\' TO START';
        $msg.textContent = (this.#endDate - this.#startDate) + 'ms';
    }
}

const wasd = new WASD();

document.addEventListener('keydown', keyDownEvent);
document.addEventListener('keyup', keyUpEvent);

function keyDownEvent(e) {
    if(e.key == 37 || e.key == "ArrowRight") {
        $RIGHT.style['background-color'] = '#eee';
    } else if(e.key == 39 || e.key == "ArrowLeft") {
        $LEFT.style['background-color'] = '#eee';
    } else if(e.key == 38 || e.key == "ArrowUp") {
        $UP.style['background-color'] = '#eee';
    } else if(e.key == 40 || e.key == "ArrowDown") {
        $DOWN.style['background-color'] = '#eee';
    } else if (e.key == 32 || e.key == 'SpaceBar' || e.key == ' ') {
        if (wasd.getState() === 'stop') {
            wasd.startWasd();
        }
    }
}

function keyUpEvent(e) {
    if(e.key == 37 || e.key == "ArrowRight") {
        $RIGHT.style['background-color'] = 'cadetblue';
        wasd.checkArrow('RIGHT');
    } else if(e.key == 39 || e.key == "ArrowLeft") {
        $LEFT.style['background-color'] = 'cadetblue';
        wasd.checkArrow('LEFT');
    } else if(e.key == 38 || e.key == "ArrowUp") {
        $UP.style['background-color'] = 'cadetblue';
        wasd.checkArrow('UP');
    } else if(e.key == 40 || e.key == "ArrowDown") {
        $DOWN.style['background-color'] = 'cadetblue';
        wasd.checkArrow('DOWN');
    }
}