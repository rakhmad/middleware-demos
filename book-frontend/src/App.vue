<template>
  <Layout>
    <BookList :books="books" />
  </Layout>
</template>

<script>
import Layout from "./components/Layout.vue"
import BookList from "./components/BookList.vue"

import axios from "axios"

export default {
  name: 'App',
  components: {
    Layout,
    BookList,
  },
  data() {
    return {
      baseUrl: import.meta.env.VITE_BOOKS_API,
      books: [],
    }
  },
  methods: {
    async fetchBooks() {
      try {
        const api_url = `${this.baseUrl}/books`
        const response = await axios.get(api_url)
        const results = response.data
        this.books = results.map(book => ({
          id: book.id,
          title: book.title,
          author: book.author,
          isbn_code: book.isbn_code,
          publisher: book.publisher,
        }))
      } catch (err) {
        if(err.response) {
          console.log("Server error:", err)
        } else if (err.request) {
          console.log("Network error:", err)
        } else {
          console.log("Client error:", err)
        }
      }
    }
  },
  mounted() {
    
    this.fetchBooks()
  }
}
</script>