import React from "react";
import {Link} from 'react-router-dom';

const book = (props) => {
    return (
        <>
            <table className="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Author</th>
                    <th>Available Samples</th>
                    <th>Category</th>
                </tr>
                </thead>
                <tbody>
                {props.books.map(
                    book =>
                        <tr id="one">
                            <td>{book.id}</td>
                            <td>{book.name}</td>
                            <td>{book.author.name}</td>
                            <td>{book.availableCopies}</td>
                            <td>{book.category}</td>
                            <td>
                                <button className="btn btn-primary btn-sm" onClick={()=> props.onDelete(book.id)}>
                                    Delete
                                </button>
                            </td>
                            <td>
                                <Link className={"btn btn-info ml-2"}
                                      onClick={() => props.onEdit(book.id)}
                                      to={`/books/edit/${book.id}`}>
                                    Edit
                                </Link>
                            </td>
                            <td>
                                <button className="btn btn-primary btn-sm" onClick={()=> props.markAsTaken(book.id)}>
                                    Mark as taken
                                </button>
                            </td>
                        </tr>
                )};
                </tbody>
            </table>

        </>
    );
}

export default book;
