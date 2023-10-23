const puppeteer = require('puppeteer');
function calculatePi(numPoints) {
  let numPointsInCircle = 0;

  for (let i = 0; i < numPoints; i++) {
    let x = Math.random();
    let y = Math.random();

    if (x * x + y * y <= 1) {
      numPointsInCircle++;
    }
  }

  return 4 * numPointsInCircle / numPoints;
}

let numPoints = 100000000000000000000000000000000000000000;
let pi = calculatePi(numPoints);

console.log(`The value of pi is approximately ${pi}`);
/*
(async () => {
  // Launch a non-headless Chrome browser
  const browser = await puppeteer.launch({ headless: false });

  // Open a new tab and navigate to the URL
  const page = await browser.newPage();
  await page.goto('https://humanbenchmark.com/tests/typing');
  await page.waitForTimeout(10000); // Wait for the page to load (adjust the time as needed)

  // Extract the paragraph text
  const paragraph = await page.evaluate(() => {
    const incompleteSpans = document.querySelectorAll('.incomplete, .incomplete.current');
    let text = '';
    incompleteSpans.forEach((span) => {
      text += span.textContent;
    });
    return text;
  });

  console.log(paragraph);

  // Type the paragraph using keyboard emulation
  await page.keyboard.type(paragraph);

  // Wait for user input to close the browser
  await page.waitForSelector('body'); // You can replace this with a specific selector if needed
  await page.waitForTimeout(10000); // Adjust the time as needed
  await browser.close();
})();
*/