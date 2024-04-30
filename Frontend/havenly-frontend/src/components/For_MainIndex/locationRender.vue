<template>
    <v-container>
        <div class="first-index">
            <div class="button-group d-flex">
                <div class="form-header">
                    <h5 class="text-white">Choose your desire</h5>
                </div>
                <form @submit.prevent="submit" class="form-edit">
                    <div class="row" v-for="location in locations" :key="location.location_id">
                        <div class="p-0 row-1">
                            <h1>This is form edit from location render vue</h1>
                            <v-select v-model="selectedProvince" :items="locations.map(loc => loc.province)" label="Select a province" required></v-select>
                            <v-select v-model="selectedAmphoe" :items="locations.map(loc => loc.amphoe)" label="Select an amphoe" required></v-select>
                            <v-select v-model="selectedDistrict" :items="locations.map(loc => loc.region)" label="Select a region" required></v-select>
                            <div class="form-btn-group" :hidden="!zipCode || !selectedDistrict">
                                <v-btn class="me-3 submit" type="submit">Search</v-btn>
                                <v-btn class="clear" @click="clearFields">clear</v-btn>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </v-container>
</template>

<script>
export default {
    name: "locationRender",

    data() {
        return {
            locations: [],
            selectedProvince: '',
            selectedAmphoe: '',
            selectedDistrict: '',
            zipCode: ''
        };
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
                    province: location.province,
                    amphoe: location.amphoe,
                    region: location.region
                }));
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
            this.selectedProvince = '';
            this.selectedAmphoe = '';
            this.selectedDistrict = '';
            this.zipCode = '';
        }
    }
}
</script>
