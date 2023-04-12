console.log('  /\\_/\\');
console.log(' ( o.o )');
console.log('  > ^ <');

try {
	setTimeout(() => { throw new Error('Error!'); }, 1000);
} catch(e) {
	console.error('HI , ' + e);
}