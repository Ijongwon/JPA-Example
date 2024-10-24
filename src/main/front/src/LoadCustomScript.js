import { useEffect, useState } from 'react';
import axios from 'axios';

export const LoadCustomScript = ({ setLoadScript }) => {
    const [books, setBooks] = useState([]);
    const [bookForm, setBookForm] = useState({
        title: '',
        writer: '',
        genre: '',
    });
    const [genre, setGenre] = useState('');
    const [message, setMessage] = useState("");

    const fetchBooks = async () => {
        try {
            const response = await axios.get('book/getAll');
            setBooks(response.data); // data로 접근
        } catch (error) {
            console.error("데이터를 가져오는 중 오류가 발생했습니다.", error);
            setMessage("데이터 조회 중 오류가 발생했습니다.");
        }
    };

    const getByGenre = async () => {
        if (!genre.trim()) {
            fetchBooks();
            return;
        }
        try {
            const response = await axios.get(`book/genre/${genre}`);
            setBooks(response.data); // data로 접근
        } catch (error) {
            setMessage("조회 중 오류가 발생했습니다.");
        }
    };

    const submitBook = async (e) => {
        e.preventDefault();

        const newBook = {
            title: bookForm.title,
            writer: bookForm.writer,
            genre: bookForm.genre
        };

        try {
            const response = await axios.post('book/post', newBook);
            if (response.status === 200) {
                setMessage("성공입니다.");
                fetchBooks();
            } else {
                setMessage("실패입니다.");
            }
        } catch (error) {
            setMessage("저장중 오류가 발생했습니다.");
        }

        setBookForm({
            title: "",
            writer: "",
            genre: ""
        });
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setBookForm((prevForm) => ({
            ...prevForm,
            [name]: value,
        }));
    };

    const handleSearchChange = (e) => {
        setGenre(e.target.value); // 검색 입력 값 업데이트
    };

    useEffect(() => {
        fetchBooks(); // 컴포넌트 마운트 시 책 목록 조회
    }, []);

    return (
        <div>
            <h2>책 목록</h2>
            <form onSubmit={submitBook}>
                <div>
                    <label htmlFor="title">제목:</label>
                    <input
                        type="text"
                        id="title"
                        name="title"
                        value={bookForm.title}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="writer">작가:</label>
                    <input
                        type="text"
                        id="writer"
                        name="writer"
                        value={bookForm.writer}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="genre">장르:</label>
                    <input
                        type="text"
                        id="genre"
                        name="genre"
                        value={bookForm.genre}
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
                    placeholder="검색할 장르를 입력하세요"
                    value={genre}
                    onChange={handleSearchChange}
                />
                <button onClick={getByGenre}>조회</button>
            </div>
            {message && <p>{message}</p>}
            <button onClick={() => setLoadScript(false)}>돌아가기</button>
            <h3>책 리스트</h3>
            <table>
                <thead>
                <tr>
                    <th>책번호</th>
                    <th>제목</th>
                    <th>작가</th>
                    <th>장르</th>
                </tr>
                </thead>
                <tbody>
                {books.map(book => (
                    <tr key={book.BookNum}>
                        <td>{book.BookNum}</td>
                        <td>{book.title}</td>
                        <td>{book.writer}</td>
                        <td>{book.genre}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};