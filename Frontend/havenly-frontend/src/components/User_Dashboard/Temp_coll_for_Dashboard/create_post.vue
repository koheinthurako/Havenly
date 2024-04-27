<template>
    <div class="create-post-section">
        <div class="row">
            <div class="col-md-7 p-0">
                <!-- Create from start -->
                <div class="create-post">
                    <div class="form-header">
                        <v-icon>mdi-information</v-icon>
                        <p class="mt-3 ms-2">Create post</p>
                    </div>

                    <div class="form-body">
                        <form @submit.prevent="submit" class="w-100">
                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <span class="float-right mt-2">Title <span class="text-red">*</span></span>
                                </div>
                                <div class="col-md-8 col-sm-12">
                                    <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                        rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                        v-model="title.value.value" :counter="10"
                                        :error-messages="title.errorMessage.value" placeholder="Title"></v-text-field>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <span class="float-right mt-2">Type <span class="text-red">*</span></span>
                                </div>
                                <div class="col-md-8 col-sm-12">
                                    <v-select bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="selectTypes.value.value"
                                        :error-messages="selectTypes.errorMessage.value" :items="Types"
                                        placeholder="Select a type"></v-select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Floor <span class="text-red">*</span></span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">

                                    <v-select bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="floor_data.value.value"
                                        :error-messages="floor_data.errorMessage.value" :items="floor_item"
                                        placeholder="Select a floor"></v-select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <span class="float-right mt-2">Finish State</span>
                                </div>
                                <div class="col-md-8 col-sm-12">
                                    <v-radio-group v-model="finishRadio.value.value"
                                        :error-messages="finishRadio.errorMessage.value" inline>
                                        <v-radio label="Complete" value="complete" color="orange"></v-radio>
                                        <v-radio label="No Complete" value="no complete" color="orange"></v-radio>

                                    </v-radio-group>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Currency</span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">
                                    <v-radio-group inline v-model="currencyRadio.value.value"
                                        :error-messages="currencyRadio.errorMessage.value">
                                        <v-radio label="Myanmar(kyat)" value="myanmar kyat" color="orange"></v-radio>
                                        <v-radio label="Thai (baht)" value="thailand baht" color="orange"></v-radio>
                                        <v-radio label="US (Dollar)" value="us dollar" color="orange"></v-radio>
                                    </v-radio-group>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <span class="float-right mt-2">Price kyat(Lakh)<span
                                            class="text-red">*</span></span>
                                </div>
                                <div class="col-md-8 col-sm-12">
                                    <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                        rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                        v-model="price.value.value" :error-messages="price.errorMessage.value"
                                        placeholder="price"></v-text-field>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <span class="float-right mt-2">Price Per Sqrt Ft</span>
                                </div>
                                <div class="col-md-8 col-sm-12">
                                    <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                        rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                        v-model="pricePerSqrtFt.value.value" placeholder="optional"></v-text-field>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <span class="float-right mt-2">Preice Per Acre</span>
                                </div>
                                <div class="col-md-8 col-sm-12">
                                    <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                        rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                        v-model="pricePerAcre.value.value" placeholder="optional"></v-text-field>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Bank Installment</span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">
                                    <v-radio-group inline v-model="bankInstallRadio.value.value"
                                        :error-messages="bankInstallRadio.errorMessage.value">
                                        <v-radio label="No" value="no" color="orange"></v-radio>
                                        <v-radio label="Yes" value="yes" color="orange"></v-radio>
                                    </v-radio-group>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <span class="float-right mt-2">Area Type</span>
                                </div>
                                <div class="col-md-8 col-sm-12 d-flex justify-space-between">

                                    <v-text-field bg-color="#EDEDED" class="w-50" filled variant="solo"
                                        density="compact" rounded="lg" clear-icon="mdi-close-circle"
                                        v-model="width.value.value" :error-messages="width.errorMessage.value"
                                        placeholder="width"></v-text-field>

                                    <v-text-field bg-color="#EDEDED" class="w-50" filled variant="solo"
                                        density="compact" rounded="lg" clear-icon="mdi-close-circle"
                                        v-model="height.value.value" :error-messages="height.errorMessage.value"
                                        placeholder="height"></v-text-field>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Furnished or not<span
                                            class="text-red">*</span></span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">

                                    <v-select bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="Furnish.value.value"
                                        :error-messages="Furnish.errorMessage.value" :items="furnish_item"
                                        placeholder="Select a type"></v-select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Bedroom</span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">

                                    <v-select bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="Bedroom.value.value"
                                        :items="bedroom_item" placeholder="Select room"></v-select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Bathroom</span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">

                                    <v-select bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="Bathroom.value.value"
                                        :items="bedroom_item" placeholder="Select room"></v-select>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Country<span class="text-red">*</span></span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">

                                    <v-select bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="Country.value.value"
                                        :error-messages="Country.errorMessage.value" :items="country_item"
                                        placeholder="Select a Country"></v-select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Region, State<span class="text-red">*</span></span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">

                                    <v-select bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="regionState.value.value"
                                        :error-messages="regionState.errorMessage.value" :items="region_state"
                                        placeholder="Select a Region"></v-select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Contact, Phone<span class="text-red">*</span></span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">

                                    <v-text-field bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle"
                                        clearable variant="solo" rounded="lg" density="compact"
                                        v-model="phone.value.value" :counter="11"
                                        :error-messages="phone.errorMessage.value"
                                        placeholder="Phone Number"></v-text-field>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Advertiser<span class="text-red">*</span></span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">
                                    <v-radio-group inline v-model="Advertiser.value.value"
                                        :error-messages="Advertiser.errorMessage.value">
                                        <v-radio label="Agent" value="Agent" color="orange"></v-radio>
                                        <v-radio label="Owner" value="Owner" color="orange"></v-radio>
                                    </v-radio-group>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Description<span class="text-red">*</span> </span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">
                                    <v-textarea bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="Description.value.value"
                                        :counter="200" :error-messages="Description.errorMessage.value"
                                        placeholder="Description"></v-textarea>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Choose Option<span class="text-red">*</span> </span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">
                                    <v-radio-group inline v-model="sellorRent.value.value"
                                        :error-messages="sellorRent.errorMessage.value">
                                        <v-radio label="Sell Post" value="Sell" color="orange" selected></v-radio>
                                        <v-radio label="Rent Post" value="Rent" color="orange"></v-radio>
                                        <v-radio label="Both Sell and Rent" value="Both" color="orange"></v-radio>

                                    </v-radio-group>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-sm-12 py-0">
                                    <span class="float-right mt-2">Choose Image<span class="text-red">*</span> </span>
                                </div>
                                <div class="col-md-8 col-sm-12 py-0">
                                    <v-file-input counter multiple color="deep-purple-accent-4" chips
                                        truncate-length="15" v-model="image.value.value"
                                        :error-messages="image.errorMessage.value" :rules="rules"
                                        accept="image/png, image/jpeg, image/bmp" placeholder="Pick an avatar"
                                        prepend-icon="mdi-camera"></v-file-input>
                                </div>
                            </div>


                            <div class="w-100 d-flex mt-3 justify-content-end">
                                <v-btn class="me-4" type="submit" rounded="xl" elevation="20" color="#E86F52">
                                    submit
                                </v-btn>

                                <v-btn @click="handleReset" rounded="xl" elevation="20" color="#525252">
                                    clear
                                </v-btn>
                            </div>
                        </form>

                    </div>

                </div>
                <!-- Create form end -->
            </div>

            <div class="col-md-5">

                <div class="display-post-section">
                    <!-- Show Created post start -->
                    <div class="w-100 d-flex p-0 m-0">
                        <span class="mt-2 me-2">Choose an option : </span>
                        <v-radio-group v-model="change_type" inline>
                            <v-radio label="Sell posts" value="sell" color="orange"></v-radio>
                            <v-radio label="Rent Posts" value="rent" color="orange"></v-radio>
                        </v-radio-group>
                    </div>

                    <div v-if="change_type === 'sell'">
                        <div v-for="data in sell_data" :key="data">

                            <div class="display-post">
                                <div class="row p-0">
                                    <div class="col-6 p-0">
                                        <div class="display-left">
                                            <v-img :src="data.img"></v-img>
                                            <div class="overlay d-flex mx-auto">
                                                <v-btn rounded elevation="10" color="warning" class="me-2">Edit
                                                    post</v-btn>
                                                <v-btn rounded elevation="10" color="#E86F52" class="">Delete
                                                    post</v-btn>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-6 p-0">
                                        <div class="display-right">

                                            <h5 class="text-left my-0 px-0">{{ data.title }}</h5>
                                            <span class="text-grey mb-4">hello world</span>

                                            <p style="line-height: 16px;">Lorem ipsum dolor sit, amet consectetur
                                                adipisicing elit. Enim iure, ea cum,
                                                inventore libero nemo saepe sit optio repellendus.....</p>
                                            <div class="d-flex w-100 justify-space-between">
                                                <span class="fw-bold"><v-icon>mdi-currency-usd</v-icon>1345
                                                    Lakh(Kyats)</span>
                                                <span class="me-3 fw-bold" style="color: #E86F52;">{{ data.type
                                                    }}</span>
                                            </div>
                                            <v-divider class="mx-auto mb-1 mt-2" :thickness="2"></v-divider>
                                            <div class="px-2 d-flex justify-space-between">
                                                <div><v-icon color="#E86F52">mdi-eye</v-icon> <span
                                                        class="mt-1">322</span>
                                                </div>
                                                <div><v-icon color="#E86F52">mdi-clock-time-three-outline</v-icon><span
                                                        class="ms-1 mt-1">3d 8h
                                                        56m</span></div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div v-else-if="change_type === 'rent'">
                        <div v-for="data in rent_data" :key="data">

                            <div class="display-post">
                                <div class="row p-0">
                                    <div class="col-6 p-0">
                                        <div class="display-left">
                                            <v-img :src="data.img"></v-img>
                                            <div class="overlay d-flex mx-auto">
                                                <v-btn rounded elevation="10" color="warning" class="me-2">Edit
                                                    post</v-btn>
                                                <v-btn rounded elevation="10" color="#E86F52" class="">Delete
                                                    post</v-btn>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-6 p-0">
                                        <div class="display-right">

                                            <h5 class="text-left my-0 px-0">{{ data.title }}</h5>
                                            <span class="text-grey mb-4">hello world</span>

                                            <p style="line-height: 16px;">Lorem ipsum dolor sit, amet consectetur
                                                adipisicing elit. Enim iure, ea cum,
                                                inventore libero nemo saepe sit optio repellendus.....</p>
                                            <div class="d-flex w-100 justify-space-between">
                                                <span class="fw-bold"><v-icon>mdi-currency-usd</v-icon>1345
                                                    Lakh(Kyats)</span>
                                                <span class="me-3 fw-bold" style="color: #E86F52;">{{ data.type
                                                    }}</span>
                                            </div>
                                            <v-divider class="mx-auto mb-1 mt-2" :thickness="2"></v-divider>
                                            <div class="px-2 d-flex justify-space-between">
                                                <div><v-icon color="#E86F52">mdi-eye</v-icon> <span
                                                        class="mt-1">322</span>
                                                </div>
                                                <div><v-icon color="#E86F52">mdi-clock-time-three-outline</v-icon><span
                                                        class="ms-1 mt-1">3d 8h
                                                        56m</span></div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!-- Show Created Post end -->
                </div>

            </div>
        </div>
    </div>

</template>

<script>
export default {
    name: 'create_post',
    data: () => ({

        change_type: 'sell',

        sell_data: [

            {
                id: 1, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-1.jpg'), type: 'Sell'
            },
            {
                id: 2, title: 'Apartment Zone', img: require('@/assets/img/house-2.jpg'), type: 'Sell'
            },
            {
                id: 3, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-3.jpg'), type: 'Sell'
            }, {
                id: 4, title: 'Apartment Zone', img: require('@/assets/img/house-4.jpg'), type: 'Sell'
            },
            {
                id: 5, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-5.jpg'), type: 'Sell'
            }, {
                id: 6, title: 'Apartment Zone', img: require('@/assets/img/house-6.jpg'), type: 'Sell'
            },
            {
                id: 7, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-7.jpg'), type: 'Sell'
            }, {
                id: 8, title: 'Apartment Zone', img: require('@/assets/img/house-1.jpg'), type: 'Sell'
            }

        ],

        rent_data: [

            {
                id: 1, title: 'International Playground', img: require('@/assets/img/house-7.jpg'), type: 'Rent'
            },
            {
                id: 2, title: 'Swimming Pool', img: require('@/assets/img/house-6.jpg'), type: 'Rent'
            },
            {
                id: 3, title: 'Shopping Mall', img: require('@/assets/img/house-5.jpg'), type: 'Rent'
            }, {
                id: 4, title: 'Apartment Zone', img: require('@/assets/img/house-4.jpg'), type: 'Rent'
            },
            {
                id: 5, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-3.jpg'), type: 'Sell'
            }, {
                id: 6, title: 'Apartment Zone', img: require('@/assets/img/house-2.jpg'), type: 'Rent'
            },
            {
                id: 7, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-1.jpg'), type: 'Sell'
            }, {
                id: 8, title: 'Apartment Zone', img: require('@/assets/img/house-7.jpg'), type: 'Rent'
            }

        ],


        rules: [

            value => {
                return !value || !value.length || value[0].size < 2000000 || 'Avatar size should be less than 2 MB!'
            },
        ],
    }),
}
</script>


<script setup>
import { ref } from 'vue'
import { useField, useForm } from 'vee-validate'

const { handleSubmit, handleReset } = useForm({
    validationSchema: {
        title(value) {
            if (value?.length >= 10) return true

            return 'Name needs to be at least 10 characters.'
        },
        phone(value) {
            if (value?.length > 9 && /[0-9-]+/.test(value)) return true

            return 'Phone number needs to be at least 9 digits.'
        },

        selectTypes(value) {
            if (value) return true

            return 'Select an item.'
        },

        finishRadio(value) {
            if (value) return true

            return 'Select one option'
        },

        currencyRadio(value) {
            if (value) return true

            return 'Select one option'
        },

        price(value) {
            if (value) return true

            return 'Set price to your post'
        },

        pricePerSqrtFt(value) {
            if (value) return true

            return true
        },

        pricePerAcre(value) {
            if (value) return true

            return true
        },

        bankInstallRadio(value) {
            if (value) return true

            return 'Select at least one'
        },

        width(value) {
            if (value) return true

            return 'Set width'
        },

        height(value) {
            if (value) return true

            return 'Set height'
        },

        Furnish(value) {
            if (value) return true

            return 'Choose an option'
        },

        Bedroom(value) {
            if (value) return true

            return true
        },

        Bathroom(value) {
            if (value) return true

            return true
        },

        Country(value) {
            if (value) return true

            return 'Select one Country'
        },

        regionState(value) {
            if (value) return true

            return 'Select one Region'
        },

        Advertiser(value) {
            if (value) return true

            return 'Select one option'
        },

        Description(value) {
            if (value) return true

            return 'Enter about your post'
        },

        sellorRent(value) {
            if (value) return true

            return 'Select on option'
        },

        image(value) {
            if (value) return true

            return 'Upload at least one image.'
        },

        floor_data(value) {
            if (value) return true

            return 'Select one option.'
        },

    },
})

/* Radio Collection */
const finishRadio = useField('finishRadio')
const currencyRadio = useField('currencyRadio')
const bankInstallRadio = useField('bankInstallRadio')
const Advertiser = useField('Advertiser')
const sellorRent = useField('sellorRent')


/* Field collection */
const title = useField('title')
const selectTypes = useField('selectTypes')
const price = useField('price')
const pricePerSqrtFt = useField('pricePerSqrtFt')  // optional 1
const pricePerAcre = useField('pricePerAcre') // optional 2
const width = useField('width')
const height = useField('height')
const Furnish = useField('Furnish')
const Bedroom = useField('Bedroom')
const Bathroom = useField('Bathroom')
const Country = useField('Country')
const regionState = useField('regionState')
const phone = useField('phone')
const Description = useField('Description')
const image = useField('image')
const floor_data = useField('floor_data')

const Types = ref([
    'Apartment',
    'Condo',
    'Mini Condo',
    'House',
    'Industrial'
])

const bedroom_item = ref([
    '1 Room',
    '2 Rooms',
    '3 Rooms',
    '4 Rooms',
    '5 Rooms',
    '6 Rooms',
    '7 Rooms',
    '8 Rooms',
    '9 Rooms',
    '10 Rooms',
    '11 Rooms',
    '12 Rooms',
])

const floor_item = ref([
    '1th floor',
    '2th floor',
    '3th floor',
    '4th floor',
    '5th floor',
    '6th floor',
    '7th floor'
])

const country_item = ref([
    'Myanmar',
    'Thailand'
])

const region_state = ref([
    'Region 1',
    'Region 2',
    'Region 3',
    'State 1',
    'State 2',
    'State 3'
])

const furnish_item = ref([
    'No Furnish',
    'Half Furnish',
    'Full Furnish'
])

const submit = handleSubmit(values => {
    alert(JSON.stringify(values, null, 2))

})
</script>

<style>
.create-post-section {
    width: 100%;
    height: auto;

    /* Create post */
    .create-post {
        overflow: hidden;
        width: 100%;
        height: auto;
        padding: 8px 12px !important;

        background-color: #fff;

        .form-header {
            background-color: #D9EDF7;
            padding: 1px 0px;
            border-radius: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 10px;
        }

        .form-body {
            padding: 10px;
            border-radius: 10px;
            box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.3);

        }
    }

    /* Display post */
    .display-post-section {
        box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.4);
        width: 100%;
        height: 100%;
        padding: 6px 16px;
        border-radius: 10px;

        /* background: linear-gradient(to bottom left, cyan 50%, palegoldenrod 50%); */

        .display-post {
            width: 100%;
            max-height: 220px !important;
            overflow: hidden;
            background-color: #D9EDF7;
            box-shadow: 0px 5px 22px 1px rgba(0, 0, 0, 0.5);
            margin-bottom: 14px;
            border-radius: 4px;
            animation: aniOne 0.8s cubic-bezier(0.68, -0.6, 0.32, 1.6) 0s 1 normal both;

            .display-left {
                position: relative;

                .overlay {
                    width: 100%;
                    height: 24%;
                    left: 0;
                    bottom: 74px;
                    position: absolute;
                    background-color: rgba(255, 255, 255, 0.8);
                    backdrop-filter: blur(40px);
                    z-index: 1;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    opacity: 0;
                    transform: translateY(200px);
                    border-bottom-left-radius: 4px;
                    transition: opacity 0.3s ease-in, transform 0.3s ease-in;

                    .v-btn {
                        text-transform: capitalize;
                    }
                }
            }

            .display-right {
                padding: 10px 20px;
                background-color: rgba(255, 255, 255, 0.7);
                backdrop-filter: blur(30px);
                -webkit-backdrop-filter: blur(30px);
                position: relative;

                h5 {
                    color: #E86F52;
                }

                p {
                    text-indent: 30px;
                }

            }


        }

        .display-post:hover .overlay {
            opacity: 1;
            transform: translateY(0);
        }


    }
}

@keyframes aniOne {
    0% {
        animation-timing-function: ease-in;
        opacity: 0;
        transform: scale(0);
    }

    38% {
        animation-timing-function: ease-out;
        opacity: 1;
        transform: scale(1);
    }

    55% {
        animation-timing-function: ease-in;
        transform: scale(0.7);
    }

    72% {
        animation-timing-function: ease-out;
        transform: scale(1);
    }

    81% {
        animation-timing-function: ease-in;
        transform: scale(0.84);
    }

    89% {
        animation-timing-function: ease-out;
        transform: scale(1);
    }

    95% {
        animation-timing-function: ease-in;
        transform: scale(0.95);
    }

    100% {
        animation-timing-function: ease-out;
        transform: scale(1);
    }
}
</style>
