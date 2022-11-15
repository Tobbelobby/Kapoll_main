import React, {useEffect, useState} from "react";
import PollData from "../../types/Poll";
import KapollerService from "../../services/KapollerService";
import PollService from "../../services/PollService";

const PollTable: React.FC = () => {
    const [polls, setPolls] = useState<Array<PollData>>([]);
    const [currentPoll, setCurrentPoll] = useState<PollData | null>(null);
    const [currentIndex, setCurrentIndex] = useState<number>(-1);

    useEffect(() => {
        retrievePolls();
    }, []);


    const retrievePolls = () => {
        let id: string | null = sessionStorage.getItem('userId')
        if (id) {
            KapollerService.get(id)
                .then((response: any) => {
                    console.log(response)
                    setPolls(response.data.polls);
                    console.log(response.data.polls);
                })
                .catch((e: Error) => {
                    console.log(e);
                });
        }
    };

    const refreshList = () => {
        retrievePolls();
        setCurrentPoll(null);
        setCurrentIndex(-1);
    };

    const setActivePoll = (poll: PollData, index: number) => {
        setCurrentPoll(poll);
        setCurrentIndex(index);
    };

    const removePoll = (id: string) => {
        PollService.remove(id)
            .then((response: any) => {
                console.log(response.data);
                refreshList();
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };
    return (
        <></>
    )
}