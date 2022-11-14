import React, {useState, ChangeEvent} from "react";
import PollService from "../../services/PollService";
import PollData from "../../types/Poll";
import KapollerService from "../../services/KapollerService";

const AddPoll: React.FC = () => {
    const initialPoll = {
        title: "",
        question: "",
        time: 0
    };
    let id: string | null = sessionStorage.getItem('userId')
    console.log('in create poll' + id)
    const [poll, setPoll] = useState<PollData>(initialPoll)
    const [submitted, setSubmitted] = useState<boolean>(false);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        setPoll({...poll, [name]: value});
    };

    const savePoll = async () => {
        console.log(sessionStorage.getItem('userId'))
        let data = {
            title: poll.title,
            question: poll.question,
            time: poll.time
        };

        await PollService.create(data)
            .then(async (response: any) => {
                console.log(response);
                setPoll({
                    id: response.data.id,
                    title: response.data.title,
                    question: response.data.question,
                    time:response.data.time
                });
                console.log(poll)

                console.log("Created pooll");
                console.log(response.data);
                if (id) {
                    KapollerService.addPoll(id, [poll])
                        .then((response) => console.log('success'))
                        .catch((e: Error) => console.log(e));
                    setSubmitted(true);
                }
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
                    <div className="form-group">
                        <label htmlFor="time ">Time in seconds</label>
                        <input
                            type="text"
                            className="form-control"
                            id="time"
                            required
                            value={poll.time}
                            onChange={handleInputChange}
                            name="time"/>
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