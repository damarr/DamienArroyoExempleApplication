<script setup>
  import StarshipInfoCard from "@/components/StarshipInfoCard.vue";

  defineProps(['starshipsInfo'])
</script>

<template>
  <div class="ship-card-wrapper">
    <template v-for="starship in starshipsInfo">
      <StarshipInfoCard class="ship-card" :starship-info="starship"/>
    </template>
  </div>
</template>

<style scoped>
.ship-card-wrapper {
  padding: 10px;
  position: relative;
  left: -300px;
}

.ship-card {
  padding: 10px;
  margin-bottom: 10px;
}
</style>