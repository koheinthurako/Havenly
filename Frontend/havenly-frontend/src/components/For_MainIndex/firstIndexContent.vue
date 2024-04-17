<!-- <template>
    <div class="first-index">

        <div class="button-group d-flex">
            

            <form @submit.prevent="submit">

                <v-select v-model="selectTypes.value.value" :error-messages="selectTypes.errorMessage.value" :items="types"
                label="Select Types"></v-select>

                <v-select v-model="selectRegion.value.value" :error-messages="selectRegion.errorMessage.value" :items="regions"
                    label="Select Region"></v-select>

                    <v-select v-model="selectTownShip.value.value" :error-messages="selectTownShip.errorMessage.value" :items="townships"
                    label="Select TownShip"></v-select>

                <v-btn class="me-4" type="submit">
                    submit
                </v-btn>

                <v-btn @click="handleReset">
                    clear
                </v-btn>
            </form>

            
        </div>

    </div>
</template>

<script>

export default {
    name: 'mainIndexVue',

    data: () => ({
        title: 'Hello world',
        sub_title: 'sleep time ;)'
    }),


}

</script>

<script setup>
import { ref } from 'vue'
import { useField, useForm } from 'vee-validate'


const { handleSubmit, handleReset } = useForm({
    validationSchema: {

        selectTypes(value) {
            if (value) return true
            return 'Select a Type.'
        },

        selectRegion(value) {
            if (value) return true

            return 'Select a Region.'
        },

        selectTownShip(value) {
            if (value) return true

            return 'Select a Township.'
        },

    },
})


const selectTypes = useField('selectTypes')
const selectRegion = useField('selectRegion')
const selectTownShip = useField('selectTownShip')

const types = ref([
    'Condo', 
    'Apartments',
    'Stand Alone house'
])


const regions = ref([
    'Yangon',
    'Mandalay',
    'Mon'
])

const townships = ref([
    'Yangon',
    'Nay Pyi Taw',
    'TreasureT4'
])




const submit = handleSubmit(values => {
    alert(JSON.stringify(values, null, 2))
})
</script> -->

<template>
    <div class="first-index">
        <div class="button-group d-flex">
            <div class="form-header">
                <h5>Choose your desire</h5>
            </div>
            <form @submit.prevent="submit" class="form-edit">
                <v-select variant="solo" v-model="selectTypes.value.value"
                    :error-messages="selectTypes.errorMessage.value" :items="types" label="Select Types"
                    required></v-select>
                <v-select variant="solo" v-model="selectRegion.value.value"
                    :error-messages="selectRegion.errorMessage.value" :items="regions" label="Select Region"
                    required></v-select>
                <v-select variant="solo" v-model="selectTownShip.value.value"
                    :error-messages="selectTownShip.errorMessage.value" :items="townships" label="Select TownShip"
                    required></v-select>
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

const types = ref(['Condo', 'Apartments', 'Stand Alone house'])
const regions = ref(['Yangon', 'Mandalay', 'Mon'])
const townships = ref(['Yangon', 'Nay Pyi Taw', 'TreasureT4'])

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