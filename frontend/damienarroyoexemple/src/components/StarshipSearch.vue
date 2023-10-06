<script lang="ts">
import {defineComponent, ref} from 'vue'
import axios from 'axios'
import StarshipResults from "@/components/StarshipResults.vue";
import StarshipInfoCard from "@/components/StarshipInfoCard.vue";

interface Starship{
    name: String
    model: String
    starship_class: String
    manufacturer: String
    cost_in_credits: Number
    crew: String
  passengers: String
    cargo_capacity: String
  }

  const config = {
    headers: {
      'Access-Control-Allow-Origin': 'true',
      'Content-Type': 'application/json',
    },
  }
  export default defineComponent({
    name: 'StarshipSearch',
    components: {StarshipInfoCard, StarshipResults},
    data() {
      return {
        starshipInfo: [] as Starship[],
        starshipBudget: '',
        starshipClass: '',
        starshipName: '',
      }
    },
    methods: {
      async fetchStarshipsInfo(maxPrice: String ,shipClass: String, starshipName: String) {
        var url = 'http://localhost:8080/api/starships?maxPrice=' + maxPrice + '&starshipClass=' + shipClass;
        console.log(url);
        const starshipInfoResponse = await axios.get<Starship[]>(url, config)
        console.log(starshipInfoResponse)
        this.starshipInfo = starshipInfoResponse.data.filter((spaceship) =>
            spaceship.name.toLowerCase().includes(starshipName.toLowerCase()))
        this.$emit('found-starships', this.starshipInfo)
      },
      submit() {
        this.fetchStarshipsInfo(this.starshipBudget, this.starshipClass, this.starshipName)
      },
    },
    async mounted() {
      await this.fetchStarshipsInfo('', '', '')
    }
  })
</script>

<template>
  <div class="form-group search-component">
    <form @submit.prevent="submit">
      <div class="input-field">
        <label for="starshipClass">Starship class</label>
        <input id="starshipClass" class="form-control" v-model="starshipClass">
      </div>

      <div class="input-field">
        <label for="starshipBudget">Budget (Cost in credits)</label>
        <input id="starshipBudget" type="number" class="no-control" v-model="starshipBudget">
      </div>

      <button @click="submit" class="btn btn-primary">Search <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-rocket" viewBox="0 0 16 16">
        <path d="M8 8c.828 0 1.5-.895 1.5-2S8.828 4 8 4s-1.5.895-1.5 2S7.172 8 8 8Z"/>
        <path d="M11.953 8.81c-.195-3.388-.968-5.507-1.777-6.819C9.707 1.233 9.23.751 8.857.454a3.495 3.495 0 0 0-.463-.315A2.19 2.19 0 0 0 8.25.064.546.546 0 0 0 8 0a.549.549 0 0 0-.266.073 2.312 2.312 0 0 0-.142.08 3.67 3.67 0 0 0-.459.33c-.37.308-.844.803-1.31 1.57-.805 1.322-1.577 3.433-1.774 6.756l-1.497 1.826-.004.005A2.5 2.5 0 0 0 2 12.202V15.5a.5.5 0 0 0 .9.3l1.125-1.5c.166-.222.42-.4.752-.57.214-.108.414-.192.625-.281l.198-.084c.7.428 1.55.635 2.4.635.85 0 1.7-.207 2.4-.635.067.03.132.056.196.083.213.09.413.174.627.282.332.17.586.348.752.57l1.125 1.5a.5.5 0 0 0 .9-.3v-3.298a2.5 2.5 0 0 0-.548-1.562l-1.499-1.83ZM12 10.445v.055c0 .866-.284 1.585-.75 2.14.146.064.292.13.425.199.39.197.8.46 1.1.86L13 14v-1.798a1.5 1.5 0 0 0-.327-.935L12 10.445ZM4.75 12.64C4.284 12.085 4 11.366 4 10.5v-.054l-.673.82a1.5 1.5 0 0 0-.327.936V14l.225-.3c.3-.4.71-.664 1.1-.861.133-.068.279-.135.425-.199ZM8.009 1.073c.063.04.14.094.226.163.284.226.683.621 1.09 1.28C10.137 3.836 11 6.237 11 10.5c0 .858-.374 1.48-.943 1.893C9.517 12.786 8.781 13 8 13c-.781 0-1.517-.214-2.057-.607C5.373 11.979 5 11.358 5 10.5c0-4.182.86-6.586 1.677-7.928.409-.67.81-1.082 1.096-1.32.09-.076.17-.135.236-.18Z"/>
        <path d="M9.479 14.361c-.48.093-.98.139-1.479.139-.5 0-.999-.046-1.479-.139L7.6 15.8a.5.5 0 0 0 .8 0l1.079-1.439Z"/>
      </svg></button>
    </form>

    <hr class="hr" />

    <div>
      <div>
        <label for="starshipName">Filter by name</label>
        <input id="starshipName" class="form-control" v-model=starshipName @input="submit">
      </div>

      <label class="float-end">{{starshipInfo.length}} of 36 results</label>
    </div>
  </div>

</template>

<style scoped>
.input-field {
  margin-bottom: 10px;
}

.search-component {
  width: 50%
}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

input[type=number] {
  -moz-appearance: textfield;
}
</style>