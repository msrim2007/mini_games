let str = '';
str += '  /\\_/\\' + '\n';
str += ' ( o.o )' + '\n';
str += '  > ^ <'

console.log(str);

const msg = 
`  /\\_/\\
 ( o.o )
  > ^ <`;

console.log(msg);

console.log(
  `
  /\\_/\\
 ( o.o )
  > ^ <
  `
);





/*
 * DEBOUNCE TEST
*/
function btnClickEvent() {
  console.log('click');
}

function debounce(callBackFunc) {
  let timer;

  return (...args) => {
    if (!timer) callBackFunc.apply(this, args);
    clearTimeout(timer);
    timer = setTimeout(() => { timer = undefined; } , 1000);
  };
}

const $BTN = document.createElement('input');
$BTN.type = 'button';
$BTN.id = 'btn';
document.querySelector('.container').appendChild($BTN);
document.getElementById('btn').addEventListener('click', debounce(() => btnClickEvent()));

