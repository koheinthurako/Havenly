<!-- <template>
    <div class="first-index">
        <div class="button-group d-flex">
            <div class="form-header">
                <h5 class="text-white">Choose your desire</h5>
            </div>
            <form @submit.prevent="submit" class="form-edit">
                <div class="row">
                    <div class=" p-0 row-1">
                        <v-select variant="solo" v-model="selectedProvince" :items="uniqueProvinces"
                            :error-messages="selectTypes.errorMessage.value" label="Select a province"
                            required></v-select>
                        <v-select variant="solo" v-model="selectedAmphoe" :items="uniqueAmphoes"
                            :error-messages="selectRegion.errorMessage.value" :disabled="!selectedProvince"
                            label="Select an amphoe" required></v-select>
                        <v-select variant="solo" v-model="selectedRegion" :items="uniqueDistricts"
                            :error-messages="selectTownShip.errorMessage.value" :disabled="!selectedAmphoe"
                            label="Select a district" required></v-select>
                        <v-text-field variant="solo" v-model="zipCode" label="Zip Code"
                            :disabled="!selectedRegion"></v-text-field>

                        <div class="form-btn-group" :hidden="!zipCode || !selectedRegion">
                            <v-btn class="me-3 submit" type="submit">Search</v-btn>
                            <v-btn class="clear" @click="clearFields">clear</v-btn>
                        </div>
                    </div>
                    <div class=" p-0 row-2">
                        <v-select variant="solo" v-model="Country.value.value"
                            :error-messages="Country.errorMessage.value" :items="countries"
                            label="Select Country" class="bg-white"></v-select>
                        <v-select variant="solo" v-model="select.value.value"
                            :error-messages="select.errorMessage.value" :items="items" label="All Types"></v-select>
                        <v-select variant="solo" v-model="PriceFrom.value.value"
                            :error-messages="PriceFrom.errorMessage.value" :items="prices"
                            label="Price range(from)"></v-select>
                        <v-select variant="solo" v-model="PriceTo.value.value"
                            :error-messages="PriceTo.errorMessage.value" :items="prices" label="Price range(to)"
                            :disabled="ppt"></v-select>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template> -->


<template>
    <div class="first-index">
      <div class="button-group d-flex">
        <div class="form-header">
          <h5 class="text-white">Choose your desire</h5>
        </div>
        <form @submit.prevent="submit" class="form-edit">
          <div class="row">
            <div class="p-0 row-1">
              <v-select bg-color="white" v-model="selectedCountry" :items="uniqueCountries" label="Select country" required></v-select>
              <v-select bg-color="white" v-model="selectedProvince" :items="uniqueProvinces" :disabled="!selectedCountry" label="Select province" required></v-select>
              <v-select bg-color="white" v-model="selectedAmphoe" :items="uniqueAmphoes" :disabled="!selectedProvince" label="Select amphoe" required></v-select>
              <v-select bg-color="white" v-model="selectedRegion" :items="uniqueDistricts" :disabled="!selectedAmphoe" label="Select region" required></v-select>
              <div class="form-btn-group" :hidden="!selectedRegion">
                <v-btn class="me-3 submit" type="submit">Search</v-btn>
                <v-btn class="clear" @click="clearFields">Clear</v-btn>
              </div>
            </div>
            <!-- <div class="p-0 row-2">
              <v-select bg-color="white" v-model="Country.value.value" :items="countries" label="Select Country"></v-select>
              <v-select bg-color="white" v-model="select.value.value" :items="items" label="All Types"></v-select>
              <v-select bg-color="white" v-model="PriceFrom.value.value" :items="prices" label="Price range(from)"></v-select>
              <v-select bg-color="white" v-model="PriceTo.value.value" :items="prices" label="Price range(to)" :disabled="ppt"></v-select>
            </div> -->
          </div>
        </form>
      </div>
    </div>
  </template>

<script>
import json_data from '../../assets/json/thailand_location.json'

export default {
  name: 'firstIndexContent',

  data() {
    return {
      locations: [],
      users: json_data,
      selectedCountry: '',
      selectedProvince: '',
      selectedAmphoe: '',
      selectedRegion: '',
      zipCode: '',
    }
  },

  computed: {

    uniqueCountries() {
      return [...new Set(this.locations.map(location => location.country_name))];
    },

    uniqueProvinces() {
      // return [...new Set(this.locations.map(location => location.province))];
      return [...new Set(this.locations.filter(location => location.country_name === this.selectedCountry).map(location => location.province))];
    },
    
    uniqueAmphoes() {
      return [...new Set(this.locations.filter(location => location.province === this.selectedProvince).map(location => location.amphoe))];
    },
    
    uniqueDistricts() {
      return [...new Set(this.locations.filter(location => location.amphoe === this.selectedAmphoe).map(location => location.region))];
    },
    
    filteredLocations() {
      return this.locations.filter(location =>
        location.country_name === this.selectedCountry &&
        location.province === this.selectedProvince &&
        location.amphoe === this.selectedAmphoe &&
        location.district === this.selectedRegion
      );
    }
  },

  mounted() {
    this.fetchLocations();
  },

  methods: {
    fetchLocations() {
      fetch('http://localhost:8083/locations/getall')
      .then(response => response.json())
      .then(data => {
        this.locations = data.map(location => ({
          location_id: location.location_id,
          country_name: location.country_name,
          province: location.province,
          amphoe: location.amphoe,
          region: location.region,
          latitude: location.latitude,
          longitude: location.longitude
        }));
        console.log(this.locations);
      })
      .catch(error => {
        console.error('Error fetching locations:', error);
      });
    },

    submit() {
      // Handle form submission
      console.log('Form submitted');
    },

    clearFields() {
      // Clear selected fields
      this.selectedCountry = '';
      this.selectedProvince = '';
      this.selectedAmphoe = '';
      this.selectedRegion = '';
      this.zipCode = '';
    }
  }
}
</script>



<!-- <script setup>
import { ref, watch } from 'vue'
import { useField, useForm } from 'vee-validate'

let ppt = true

const { handleSubmit } = useForm({
    validationSchema: {
        selectTypes(value) {
            if (value) {
                return true;
            } else {
                return 'Select an item.'
            }
        },

        select(value) {
            if (value) return true

            return 'Select an item.'
        },

        selectRegion(value) {
            if (value) {
                return true;
            } else {
                return 'Select an item.'
            }
        },

        selectTownShip(value) {
            if (value) {
                return true;
            } else {
                return 'Select an item.'
            }
        },

        PriceFrom(value) {
            if (value) {
                ppt = false;
                return true;
            } else {
                return 'Select an item.'
            }
        },


        PriceTo(value) {
            if (value) {
                return true;
            } else {
                return 'Select an item.'
            }
        },

        Country(value) {
            if (value) {
                return true;
            } else {
                return 'Select an item.'
            }
        },

    }
})

const selectTypes = useField('selectTypes')
const selectRegion = useField('selectRegion')
const selectTownShip = useField('selectTownShip')
const select = useField('select')
const PriceFrom = useField('PriceFrom')
const PriceTo = useField('PriceTo')
const Country = useField('Country')

const items = ref([
    'Apartment',
    'Mini Condo',
    'Condo',
    'House',
    'Land',
    'Shop, Office',
    'Industrial Zone',
    'Hotel, Restaurant',
])

const countries = ref([
    'Myanmar',
    'Thailand',

])

const prices = ref([
    '100 (Lakh)',
    '200 (Lakh)',
    '300 (Lakh)',
    '400 (Lakh)',
    '500 (Lakh)',
    '600 (Lakh)',
    '700 (Lakh)',
    '800 (Lakh)',
    '900 (Lakh)',
    '1000 (Lakh)',
    '1100 (Lakh)',
    '1200 (Lakh)',
    '1300 (Lakh)',
    '1400 (Lakh)',
    '1500 (Lakh)',
])


const showSubmitButton = ref(false)

watch([selectTypes.value, selectRegion.value, selectTownShip.value], () => {
    if ((selectTypes.value.value !== '' || selectRegion.value.value !== '' || selectTownShip.value.value !== '')) {
        showSubmitButton.value = true;
    } else {
        showSubmitButton.value = false;
    }
})

const submit = handleSubmit(values => {
    alert(JSON.stringify(values, null, 2))
})

function clearFields() {
    showSubmitButton.value = false;
    selectTypes.value.value = '';
    selectRegion.value.value = '';
    selectTownShip.value.value = '';
    selectTypes.errorMessage.value = '';
    selectRegion.errorMessage.value = '';
    selectTownShip.errorMessage.value = '';
}
</script> -->

<style scoped>
.v-select .v-select__selection:hover,
.v-select .v-select__selection:focus {
    background-color: red !important;
}
</style>