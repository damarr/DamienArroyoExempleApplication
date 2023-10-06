<script lang="ts">
import {defineComponent} from "vue";
import StarshipSearch from "@/components/StarshipSearch.vue";
import StarshipResults from "@/components/StarshipResults.vue";

export default defineComponent({
  components: {StarshipResults, StarshipSearch},
  data() {
    return {
      starshipInfo: [],
    }
  },
  methods: {
    setStarshipData(data: []){
      this.starshipInfo = data;
    }
  }
})

</script>

<template>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  </head>

  <header class="side-header">
    <StarshipSearch @found-starships="setStarshipData"/>
  </header>

  <body>
    <StarshipResults :starships-info="starshipInfo"/>
  </body>
</template>

<style scoped>
</style>
