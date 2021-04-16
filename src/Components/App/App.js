
import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Book from "../book/book";
import BookEdit from "../book/bookEdit";
import LibService from "../../repository/libraryRepository"

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            counties: [],
            authors: [],
            selectedProduct: {}
        }
    }

    render() {
        return (
            <Router>
                    <main>
                        <Route path={"/books/edit/:id"} exact render={() =>
                            <BookEdit authors={this.state.authors}
                                         onEditBook={this.editBook}
                                         book={this.state.selectedProduct}/>}/>
                        <Route path={"/books"} exact render={() =>
                            <Book   books={this.state.books}
                                        onDelete = {this.deleteBook}
                                        markAsTaken = {this.markBook}
                                        onEdit = {this.getBook}/>}/>

                    </main>
            </Router>
    );
    }

    componentDidMount() {
        this.loadCountries();
        this.loadAuthors();
        this.loadBooks();
    }

    loadBooks = () => {
        LibService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }
    loadAuthors = () => {
        LibService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }
    loadCountries = () => {
        LibService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            });
    }

deleteBook = (id) =>
{
    LibService.deleteBook(id)
        .then(() =>
        {
            this.loadBooks();
        });
}
    getBook = (id) => {
        LibService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedProduct: data.data
                })
            })
    }

    editBook = (id, name, author, category, availableCopies) => {
        LibService.editBook(id, name, author, category, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    markBook = (id) =>
    {
        LibService.markBook(id)
            .then(() =>
            {
                this.loadBooks();
            });
    }

}
export default App;
