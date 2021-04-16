import axios from '../custom-axios/axios';

const LibService =
{
        fetchBooks: () => {
            return axios.get("/books");
        },
        fetchAuthors: () => {
            return axios.get("/books/authors");
        },
        fetchCountries: () => {
            return axios.get("/books/countries");
        },
         deleteBook: (id) => {
          return axios.delete(`/books/delete/${id}`);
         },
         getBook: (id) =>{
            return axios.get(`/books/${id}`);
         },
         editBook: (id, name, author, category, availableCopies) =>{
            return axios.put(`/products/edit/${id}`, {
                        "name" : name,
                        "author" : author,
                        "category" : category,
                        "availableCopies" : availableCopies
                    });
         },
         markBook: (id) =>
         {
            return axios.get(`/books/decrement/${id}`);
         }

}

export default LibService;