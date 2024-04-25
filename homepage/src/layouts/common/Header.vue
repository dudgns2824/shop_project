<script setup lang="ts">

</script>

<template>
  <v-app-bar style="background-color: #21486e;" elevation="0" height="60">
    <v-btn
        class="hidden-md-and-down mr-3"
        color="white"
        icon
        rounded="sm"
        variant="text"
        @click.stop="customizer.SET_MINI_SIDEBAR(!customizer.mini_sidebar)"
        size="small"
    >
      <MenuFoldOutlined :style="{ fontSize: '16px' }" />
    </v-btn>
    <v-btn
        class="hidden-lg-and-up ms-3"
        color="white"
        icon
        rounded="sm"
        variant="text"
        @click.stop="customizer.SET_SIDEBAR_DRAWER"
        size="small"
    >
      <MenuFoldOutlined :style="{ fontSize: '16px' }" />
    </v-btn>

    <v-menu :close-on-content-click="false" class="hidden-lg-and-up" offset="10, 0">
    </v-menu>

    <v-col sm="5"></v-col>
    <img src="@/assets/images/hiddentag.svg" width="96" />

    <v-col sm="5"></v-col>
    <NotificationDD />
    <v-menu :close-on-content-click="false" offset="8, 0">
      <template v-slot:activator="{ props }">
        <v-btn class="profileBtn" color="white" variant="text" rounded="sm" v-bind="props">
          <mdiFormatListBulletedSquare :style="{ fontSize: '24px' }" />
        </v-btn>
      </template>
      <v-sheet rounded="md" width="250">
        <ProfileDD />
      </v-sheet>
    </v-menu>
  </v-app-bar>
</template>
<style scoped>

</style>