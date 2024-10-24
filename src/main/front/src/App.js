import './App.css';
import axios from "axios";
import {useEffect, useState} from "react";
import { v4 as uuidv4 } from 'uuid';


function App() {
    const [tests, setTests] = useState([]);
    const [form, setForm] = useState({
        name: '',
        age: '',
        regiDate : '',
    });
    const [message, setMessage] = useState('');
    const [searchName, setSearchName] = useState('');

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString('ko-KR') + ' ' + date.toLocaleTimeString('ko-KR', {
            hour12: false, // 24시간 형식
            hour: '2-digit', // 두 자리로 표시
            minute: '2-digit' // 두 자리로 표시
        });
    };



    const fetchTests = async () => {
        try {
            const response = await axios.get('api/all');
            const formattedData = response.data.map(test => ({
                ...test,
                regiDate: formatDate(test.regiDate)
            }));
            setTests(formattedData);
        } catch (error) {
            console.error("데이터를 가져오는 중 오류가 발생했습니다.", error);
            setMessage("데이터 조회 중 오류가 발생했습니다.");
        }
    };

    const getByName = async () => {
        if (!searchName.trim()) {
            fetchTests();
            return;
        }
        try{
            const response = await axios.get(`api/name/${searchName}`);
            const formattedData = response.data.map(test => ({
                ...test,
                regiDate: formatDate(test.regiDate)
            }));
            setTests(formattedData);
        } catch (error) {
            setMessage("이름 조회 중 오류가 발생했습니다.")
        }
    }


    function handleChange(e) {
        const {name , value} = e.target;
        setForm((prevForm) => ({
            ...prevForm,
            [name] : value,
        }));
    }

    const handleSearchChange = (e) => {
        setSearchName(e.target.value); // 검색 입력 값 업데이트
    };

    async function submit(e) {
        e.preventDefault();

        const newTest = {
            // id: uuidv4(),
            name: form.name,
            age: parseInt(form.age),
        };



        try{
            const response = await axios.post('api/post' , newTest);
            if(response.status === 200){
                setMessage("성공입니다.");
                fetchTests();
            }else{
                setMessage("실패입니다.");
            }
        } catch (error){
            setMessage("저장중 오류가 발생했습니다.")
        }
        setForm({
            name: "",
            age : ""
        });

    }

    useEffect(() => {
        fetchTests();
    }, []);



  return (
    <div className="App">
        <header className="App-header">
            <ul>
                {tests.map(test => (
                    <li key={test.id}>
                        {test.name} - {test.age} - {test.regiDate}
                    </li>
                ))}
            </ul>
            <form onSubmit={submit}>
                <div>
                    <label htmlFor="name">이름:</label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={form.name}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="age">나이:</label>
                    <input
                        type="number"
                        id="age"
                        name="age"
                        value={form.age}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit">저장</button>
            </form>
            <div>
                <input
                    type="text"
                    placeholder="검색할 이름을 입력하세요"
                    value={searchName}
                    onChange={handleSearchChange}
                />
                <button onClick={getByName}>조회</button>
            </div>
            {message && <p>{message}</p>}
        </header>
    </div>
  );
}

export default App;
