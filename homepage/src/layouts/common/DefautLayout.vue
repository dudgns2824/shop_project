<script setup lang="ts">
import Header from "@/layouts/common/Header.vue";
import Footer from "@/layouts/common/Footer.vue";
</script>

<template>
  <v-locale-provider>
    <v-app :class="[]">
      <Header></Header>

      <v-main class="page-wrapper">
        <v-container>
          <RouterView></RouterView>
        </v-container>
        <v-container>
          <Footer></Footer>
        </v-container>
      </v-main>
    </v-app>
  </v-locale-provider>
</template>

<style scoped>

</style>