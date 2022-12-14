import React, {useEffect} from 'react';
import { useState } from 'react';
import "../../styles/PollOnline.css"

/*code derived from https://dev.to/yuridevat/how-to-create-a-timer-with-react-7b9 */
const Timer = () => {
  const [days, setDays] = useState(0);
  const [hours, setHours] = useState(0);
  const [minutes, setMinutes] = useState(0);
  const [seconds, setSeconds] = useState(0);

  const deadline = "December, 31, 2022";

  const getTime = (deadline: string) => {
    const time = Date.parse(deadline) - Date.now();

    setDays(Math.floor(time / (1000 * 60 * 60 * 24)));
    setHours(Math.floor((time / (1000 * 60 * 60)) % 24));
    setMinutes(Math.floor((time / 1000 / 60) % 60));
    setSeconds(Math.floor((time / 1000) % 60));
  };

  useEffect(() => {
    const interval = setInterval(() => getTime(deadline), 1000);

    return () => clearInterval(interval);
  }, []);

  return (
      <div className="timer flex-direction-row" role="timer">
        <div className="flex font">
            <span>Time left: &nbsp; </span>
            <div id="minute">{minutes < 10 ? "0" + minutes : minutes}:</div>
            <div id="second">{seconds < 10 ? "0" + seconds : seconds}</div>
        </div>
      </div>
  );
};

export default Timer;
