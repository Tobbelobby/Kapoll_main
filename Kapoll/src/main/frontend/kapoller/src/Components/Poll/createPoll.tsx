import React, {useState, ChangeEvent} from "react";
import PollService from "../../services/PollService";
import PollData from "../../types/Poll";

const AddPoll: React.FC = () => {
    const initialPoll = {
        title: "",
        question: "",
        time: 0
    };
    const [poll, setPoll] = useState<PollData>(initialPoll)
    const [submitted, setSubmitted] = useState<boolean>(false);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        setPoll({...poll, [name]: value});
    };

    const savePoll = () => {
        var data = {
            title: poll.title,
            question: poll.question,
            time: poll.time
        };

        PollService.create(data)
            .then((response: any) => {
                setPoll({
                    title: response.data.title,
                    question: response.data.question,
                    time: response.data.time
                });
                setSubmitted(true);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e)
            });
    };
    const newPoll = () => {
        setPoll(initialPoll);
        setSubmitted(false)
    };
    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h4>You submitted successfully!</h4>
                    <button className={'btn btn-success'} onClick={newPoll}>
                        Add
                    </button>
                </div>
            ) : (
                <div>
                    <div className={'form-group'}>
                        <label htmlFor="title">Title</label>
                        <input type="text"
                               className={"form-control"}
                               id={"title"}
                               required
                               value={poll.title}
                               onChange={handleInputChange}
                               name={"title"}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="question ">Question</label>
                        <input
                            type="text"
                            className="form-control"
                            id="question"
                            required
                            value={poll.question}
                            onChange={handleInputChange}
                            name="question"/>
                    </div>
                    <button onClick={savePoll} className="btn btn-success">
                        Submit
                    </button>
                </div>
            )}
        </div>
    );
};

export default AddPoll;