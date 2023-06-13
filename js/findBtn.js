const _BTN_SIZE = 15;
const _SCREEN_WIDTH = (window.innerWidth - _BTN_SIZE);
const _SCREEN_HEIGHT = (window.innerHeight - _BTN_SIZE);
const $container = document.querySelector('.container');
const $startBtn = document.querySelector('#start');
const $resultDiv = document.querySelector('.result');
let time;

function createNewBtn() {
    const $newDiv = document.createElement('div');
    $newDiv.style.position = 'absolute';
    $newDiv.style.top = Math.floor(Math.random() * _SCREEN_HEIGHT) + 'px';
    $newDiv.style.left = Math.floor(Math.random() * _SCREEN_WIDTH) + 'px';
    $newDiv.style.margin = 0;
    $newDiv.style.padding = 0;
    $newDiv.style.width = _BTN_SIZE + 'px';
    $newDiv.style.height = _BTN_SIZE + 'px';
    $newDiv.style.cursor = 'pointer';

    $newDiv.addEventListener('click', function() {
        time = new Date() - time;
        $resultDiv.textContent = 'time it took : ' + (time / 1000) + 's';
        $startBtn.style.display = '';
        $resultDiv.style.display = '';
        $container.removeChild(this);
    });

    return $newDiv;
}

$startBtn.addEventListener('click', function() {
    this.style.display = 'none';
    $resultDiv.style.display = 'none';
    $container.append(createNewBtn());
    time = new Date();
});