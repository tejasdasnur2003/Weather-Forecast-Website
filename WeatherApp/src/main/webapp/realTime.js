setInterval(() => {
  // Get server time and split into hours, minutes, seconds
  let serverTime = localTimeFromServer ? localTimeFromServer.split(":") : [0, 0];
  let h = parseInt(serverTime[0]) || 0;
  let m = parseInt(serverTime[1]) || 0;
  let s = new Date().getSeconds(); // Get seconds from client-side
  let ap = h >= 12 ? 'PM' : 'AM';

  // Adjust for 12-hour format
  if (h > 12) {
      h = h - 12;
  }

  // Format time to always show two digits
  h = h < 10 ? '0' + h : h;
  m = m < 10 ? '0' + m : m;
  s = s < 10 ? '0' + s : s;

  // Update the HTML elements with the new time values
  document.getElementById('hours').innerHTML = h + ' Hours';
  document.getElementById('minutes').innerHTML = m + ' Minutes';
  document.getElementById('seconds').innerHTML = s + ' Seconds';
  document.getElementById('ampm').innerHTML = ap;

  // Update the circular clock indicators
  document.getElementById('hh').style.strokeDashoffset = 440 - (440 * h) / 12;
  document.getElementById('mm').style.strokeDashoffset = 440 - (440 * m) / 60;
  document.getElementById('ss').style.strokeDashoffset = 440 - (440 * s) / 60;

  // Update the rotation of the dots on the clock
  document.querySelector('.h_dot').style.transform = `rotate(${h * 30}deg)`;
  document.querySelector('.m_dot').style.transform = `rotate(${m * 6}deg)`;
  document.querySelector('.s_dot').style.transform = `rotate(${s * 6}deg)`;
}, 1000);
