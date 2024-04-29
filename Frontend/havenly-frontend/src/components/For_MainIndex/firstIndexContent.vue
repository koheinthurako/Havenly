<template>
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
                        <v-select variant="solo" v-model="selectedDistrict" :items="uniqueDistricts"
                            :error-messages="selectTownShip.errorMessage.value" :disabled="!selectedAmphoe"
                            label="Select a district" required></v-select>
                        <v-text-field variant="solo" v-model="zipCode" label="Zip Code"
                            :disabled="!selectedDistrict"></v-text-field>

                        <div class="form-btn-group" :hidden="!zipCode || !selectedDistrict">
                            <v-btn class="me-3 submit" type="submit">Search</v-btn>
                            <v-btn class="clear" @click="clearFields">clear</v-btn>
                        </div>
                    </div>
                    <div class=" p-0 row-2">
                        <v-select variant="solo" v-model="Country.value.value"
                            :error-messages="Country.errorMessage.value" :items="countries"
                            label="Select Country"></v-select>
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
</template>

<script>
import json_data from '../../assets/json/thailand_location.json'
export default {
    name: 'firstIndexContent',

    data() {
        return {
            users: json_data,
            selectedProvince: '',
            selectedAmphoe: '',
            selectedDistrict: '',
            zipCode: '',
        }
    },

    computed: {
        uniqueProvinces() {

            return [...new Set(this.users.locations.map(user => user.province))];
        },
        uniqueAmphoes() {

            if (this.selectedProvince) {
                return [...new Set(this.users.locations.filter(user => user.province === this.selectedProvince).map(user => user.amphoe))];
            } else {
                return [];
            }
        },
        uniqueDistricts() {
            if (this.selectedAmphoe) {
                return [...new Set(this.users.locations.filter(user => user.amphoe === this.selectedAmphoe).map(user => user.district))];
            } else {
                return [];
            }
        },
        filteredLocations() {

            return this.users.locations.filter(user =>
                user.province === this.selectedProvince &&
                user.amphoe === this.selectedAmphoe &&
                user.district === this.selectedDistrict
            );
        }
    },
    watch: {
        filteredLocations() {
            this.zipCode = this.filteredLocations.length > 0 ? this.filteredLocations[0].zipcode : '';
        }
    }
}


</script>

<script setup>
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
</script>

<style scoped>
.v-select .v-select__selection:hover,
.v-select .v-select__selection:focus {
    background-color: red !important;
}
</style>

<!-- 
<template>
    <div class="first-index">
        <div class="button-group d-flex">
            <div class="form-header">
                <h5 class="text-white">Choose your desire</h5>
            </div>
            <form @submit.prevent="submit" class="form-edit">
                <v-select variant="solo" v-model="selectedProvince" :items="uniqueProvinces"
                    :error-messages="selectTypes.errorMessage.value" label="Select a province" required></v-select>
                <v-select variant="solo" v-model="selectedAmphoe" :items="uniqueAmphoes"
                    :error-messages="selectRegion.errorMessage.value" :disabled="!selectedProvince"
                    label="Select an amphoe" required></v-select>
                <v-select variant="solo" v-model="selectedDistrict" :items="uniqueDistricts"
                    :error-messages="selectTownShip.errorMessage.value" :disabled="!selectedAmphoe"
                    label="Select a district" required></v-select>
                <v-text-field variant="solo" v-model="zipCode" label="Zip Code"
                    :disabled="!selectedDistrict"></v-text-field>


                <div class="form-btn-group" :hidden="!showSubmitButton">
                    <v-btn class="me-3 submit" type="submit">Search</v-btn>
                    <v-btn class="clear" @click="clearFields">clear</v-btn>
                </div>
            </form>
        </div>
    </div>
</template>
<script setup>
import { ref, watch } from 'vue'
import { useField, useForm } from 'vee-validate'
import json_data from '../../assets/json/thailand_location.json'

const users = json_data

const selectedProvince = ref('')
const selectedAmphoe = ref('')
const selectedDistrict = ref('')
const zipCode = ref('')

const uniqueProvinces = ref([...new Set(users.locations.map(user => user.province))])
const uniqueAmphoes = ref([])
const uniqueDistricts = ref([])
const filteredLocations = ref([])

const { handleSubmit } = useForm({
    validationSchema: {
        selectTypes(value) {
            if (value) {
                return true;
            } else {
                return 'Select an item.'
            }
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


    }
})

const selectTypes = useField('selectTypes')
const selectRegion = useField('selectRegion')
const selectTownShip = useField('selectTownShip')


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

watch(selectedProvince, () => {
    if (selectedProvince.value) {
        uniqueAmphoes.value = [...new Set(users.locations.filter(user => user.province === selectedProvince.value).map(user => user.amphoe))];
        selectedAmphoe.value = ''; // Reset selected amphoe when province changes
    }
})

watch(selectedAmphoe, () => {
    if (selectedAmphoe.value) {
        uniqueDistricts.value = [...new Set(users.locations.filter(user => user.amphoe === selectedAmphoe.value).map(user => user.district))];
        selectedDistrict.value = ''; // Reset selected district when amphoe changes
    }
})

watch([selectedProvince, selectedAmphoe, selectedDistrict], () => {
    if (selectedProvince.value && selectedAmphoe.value && selectedDistrict.value) {
        filteredLocations.value = users.locations.filter(user =>
            user.province === selectedProvince.value &&
            user.amphoe === selectedAmphoe.value &&
            user.district === selectedDistrict.value
        );
    } else {
        filteredLocations.value = []; // Reset filteredLocations if any of the selections are empty
    }
});

</script> -->
