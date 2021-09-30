<template>
  <Header />
  <main class="container flex-grow px-4 mx-auto my-12">
    <slot />
  </main>
  <Footer />
</template>

<script>
import Header from "./Header.vue"
import Footer from "./Footer.vue"

export default {
  components: {
    Header,
    Footer,
  },
}
</script>