const $btn = document.querySelector('.main');
const $msg = document.querySelector('h3');

class ReadySteadyBang {
    #state = 'start';
    #timeId = 0;
    #startTime = 0;
    #endTime = 0;

    getState() {
        return this.#state;
    }

    getTimeId() {
        return this.#timeId;
    }
    
    setReady() {
        this.#state = 'ready';
        $btn.querySelector('h1').textContent = 'READY..';
        this.#timeId = setTimeout(() => this.setSteady(), ReadySteadyBang.getRandSecond());
    }

    setSteady() {
        this.#state = 'steady';
        $btn.querySelector('h1').textContent = 'STEADY...';
        this.#timeId = setTimeout(() => this.setBang(), ReadySteadyBang.getRandSecond());
    }

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
        if (endTime) {
            this.#endTime = endTime;
            $msg.textContent = 'Reaction rate : ' + (this.#endTime - this.#startTime) + 'ms';
        } else {
            $msg.textContent = 'DONT RUSH...';
        }
    }

    static getRandSecond() {
        return Math.floor(Math.random() * 4501) + 1500;
    }
}

const rsb = new ReadySteadyBang();

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