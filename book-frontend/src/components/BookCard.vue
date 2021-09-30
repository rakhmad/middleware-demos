<template>
  <section class="p-4 rounded-lg shadow-lg bg-gray-50 w-80">
    <div class="h-48">
      <p class="text-xl font-bold text-left text-green-800">
        {{ book.title }}
      </p>
      <p class="mt-2 font-bold text-lg text-justify text-gray-700 line-clamp-4">
        {{ book.author }}
      </p>
      <p class="mt-7 font-bold text-red-600">
          {{ book.isbn_code }}
      </p>
      <p class="mt-1 font-light text-blue-600">
        {{ book.publisher }}
      </p>
    </div>
  </section>
</template>

<script>
export default {
    props: {
        book: {
            type: Object,
            required: true,
        }
    }
}
</script>
