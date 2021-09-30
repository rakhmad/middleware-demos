<template>
  <div
    class="grid grid-cols-1 gap-6 mt-4 md:grid-cols-2 lg:grid-cols-3 2xl:grid-cols-4 justify-items-center"
  >
    <BookCard 
        v-for="book in books"
        v-bind:book="book"
        v-bind:key="book.id"
    ></BookCard>
  </div>
</template>

<script>
import BookCard from "./BookCard.vue"
export default {
  props: {
    books: {
      type: Array,
      required: true,
    },
  },
  components: {
    BookCard,
  },
}
</script>