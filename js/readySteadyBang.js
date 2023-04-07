// html elements
const $btn = document.querySelector('.main');
const $msg = document.querySelector('h3');

// class for reaction game
class ReadySteadyBang {
    #state = 'start';
    #timeId = 0;
    #startTime = 0;
    #endTime = 0;

    // methods(prototype method)
    getState() {
        return this.#state;
    }

    getTimeId() {
        return this.#timeId;
    }
    
    // if state ready : prepare to change state to 'steady' (random delay)
    setReady() {
        this.#state = 'ready';
        $btn.querySelector('h1').textContent = 'READY..';
        this.#timeId = setTimeout(() => this.setSteady(), ReadySteadyBang.getRandSecond());
    }

    // if state steady : prepare to change state to 'bang' (random delay)
    setSteady() {
        this.#state = 'steady';
        $btn.querySelector('h1').textContent = 'STEADY...';
        this.#timeId = setTimeout(() => this.setBang(), ReadySteadyBang.getRandSecond());
    }

    // if state bang : check time start until user click
    setBang() {
        this.#state = 'bang';
        $btn.style.background = 'yellowgreen';
        $btn.querySelector('h1').textContent = 'BANG!';
        this.#startTime = new Date();
    }

    setStart(endTime) {
        this.#state = 'start';
        $btn.style.background = '#eee';
        $btn.querySelector('h1').textContent = 'START';
        // argument 'endTime' is not falsy. the game state was 'bang'
        if (endTime) {
            // check clicked date and calc reaction rate
            this.#endTime = endTime;
            $msg.textContent = 'Reaction rate : ' + (this.#endTime - this.#startTime) + 'ms';
        } else { // if argument 'endTime' is falsy. the game state was not 'bang'
            $msg.textContent = 'DONT RUSH...';
        }
    }

    // static method for get random number for 1.5 ~ 6
    static getRandSecond() {
        return Math.floor(Math.random() * 4501) + 1500;
    }
}

const rsb = new ReadySteadyBang();

// if user click btn area
$btn.onclick = function() {
    const endTime = new Date();
    clearTimeout(rsb.getTimeId());
    switch(rsb.getState()) {
        case 'start':
            $msg.textContent = '';
            rsb.setReady();
            break;
        case 'ready':
            rsb.setStart();
            break;
        case 'steady':
            rsb.setStart();
            break;
        case 'bang':
            rsb.setStart(endTime);
            break;
    }
};