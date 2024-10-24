import './App.css';
import axios from "axios";
import { useEffect, useState } from "react";
import { LoadCustomScript } from "./LoadCustomScript";

function App() {
    const [tests, setTests] = useState([]);
    const [testForm, setTestForm] = useState({
        name: '',
        age: '',
    });
    const [message, setMessage] = useState('');
    const [searchName, setSearchName] = useState('');
    const [showCustomScript, setShowCustomScript] = useState(false); // 스크립트 로드 여부 상태 추가

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString('ko-KR') + ' ' + date.toLocaleTimeString('ko-KR', {
            hour12: false,
            hour: '2-digit',
            minute: '2-digit'
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
        try {
            const response = await axios.get(`api/name/${searchName}`);
            const formattedData = response.data.map(test => ({
                ...test,
                regiDate: formatDate(test.regiDate)
            }));
            setTests(formattedData);
        } catch (error) {
            setMessage("이름 조회 중 오류가 발생했습니다.");
        }
    };

    function handleChange(e) {
        const { name, value } = e.target;
        setTestForm((prevForm) => ({
            ...prevForm,
            [name]: value,
        }));
    }

    const handleSearchChange = (e) => {
        setSearchName(e.target.value);
    };

    async function submitTest(e) {
        e.preventDefault();
        const newTest = {
            name: testForm.name,
            age: parseInt(testForm.age),
        };
        try {
            const response = await axios.post('api/post', newTest);
            if (response.status === 200) {
                setMessage("성공입니다.");
                fetchTests();
            } else {
                setMessage("실패입니다.");
            }
        } catch (error) {
            setMessage("저장중 오류가 발생했습니다.");
        }
        setTestForm({ name: "", age: "" });
    }

    useEffect(() => {
        fetchTests();
    }, []);

    return (
        <div className="App">
            <header className="App-header">
                {!showCustomScript ? (
                    <>
                        <table>
                            <thead>
                            <tr>
                                <th>이름</th>
                                <th>나이</th>
                                <th>등록 날짜</th>
                            </tr>
                            </thead>
                            <tbody>
                            {tests.map(test => (
                                <tr key={test.id}>
                                    <td>{test.name}</td>
                                    <td>{test.age}</td>
                                    <td>{test.regiDate}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                        <form onSubmit={submitTest}>
                            <div>
                                <label htmlFor="name">이름:</label>
                                <input
                                    type="text"
                                    id="name"
                                    name="name"
                                    value={testForm.name}
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
                                    value={testForm.age}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <button type="submit">저장</button>
                        </form>

                        <div>
                            <label htmlFor="search">검색어:</label>
                            <input
                                type="text"
                                placeholder="검색할 이름을 입력하세요"
                                value={searchName}
                                onChange={handleSearchChange}
                            />
                            <button onClick={getByName}>조회</button>
                        </div>
                        {message && <p>{message}</p>}
                        <button onClick={() => setShowCustomScript(true)}>JPA 커스텀 스크립트 로드</button>
                    </>
                ) : (
                    <LoadCustomScript setLoadScript={setShowCustomScript} />
                )}
            </header>
        </div>
    );
}

export default App;