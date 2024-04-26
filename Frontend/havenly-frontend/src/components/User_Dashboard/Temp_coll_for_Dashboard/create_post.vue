<!-- <template>
    <div class="create-post">
        <div class="create-post-header">
            <div class="d-flex">
                <v-icon>mdi-information</v-icon>&nbsp;
                <h5>For sale</h5>
            </div>
        </div>
        <br>
        <div class="create-post-body">

            <form @submit.prevent="submit">
                <v-text-field v-model="name.value.value" :counter="10" :error-messages="name.errorMessage.value"
                    label="Title"></v-text-field>

                <v-select v-model="select.value.value" :error-messages="select.errorMessage.value" :items="Types"
                    label="Select Types"></v-select>

                <v-text-field v-model="phone.value.value" :counter="7" :error-messages="phone.errorMessage.value"
                    label="Phone Number"></v-text-field>

                <v-text-field v-model="email.value.value" :error-messages="email.errorMessage.value"
                    label="E-mail"></v-text-field>

                <v-checkbox v-model="checkbox.value.value" :error-messages="checkbox.errorMessage.value" label="Option"
                    type="checkbox" value="1"></v-checkbox>

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
    name: 'create_post',

    data: () => ({
        selectedType: '',
    }),

    watch: {
        selectedType(newValue) {
            if (newValue === 'Condo') {
                this.phone.value = '';
            }
        }
    }
}
</script>

<script setup>
import { ref } from 'vue'
import { useField, useForm } from 'vee-validate'

const { handleSubmit, handleReset } = useForm({
    validationSchema: {
        name(value) {
            if (value?.length >= 2) return true

            return 'Name needs to be at least 2 characters.'
        },
        phone(value) {
            if (value?.length > 9 && /[0-9-]+/.test(value)) return true

            return 'Phone number needs to be at least 9 digits.'
        },
        email(value) {
            if (/^[a-z.-]+@[a-z.-]+\.[a-z]+$/i.test(value)) return true

            return 'Must be a valid e-mail.'
        },
        select(value) {
            if (value) return true

            return 'Select an item.'
        },
        checkbox(value) {
            if (value === '1') return true

            return 'Must be checked.'
        },
    },
})
const name = useField('name')
const phone = useField('phone')
const email = useField('email')
const select = useField('select')
const checkbox = useField('checkbox')

const Types = ref([
    'Apartment',
    'Mini Condo',
    'Condo',
    'House',
    'Land',
    'Shop Office',
    'Industrial zone',
    'Hotel, Restaurant'
])

const submit = handleSubmit(values => {
    alert(JSON.stringify(values, null, 2))
})
</script>

<style>
.create-post {
    width: 100%;
    height: auto;
    padding: 20px 30px;
    background-color: #fff;


    .create-post-header {
        width: 100%;
        height: auto;
        padding: 10px 0px;
        display: flex;
        justify-content: center;
        background-color: #D9EDF7;
        border-radius: 10px;
        margin-bottom: 10px;
    }

    .create-post-body {
        border-radius: 10px;
        box-shadow: 0px 4px 20px -1px rgba(0, 0, 0, 0.3);
        padding: 30px;
        background-color: #F5F5F5;

        .slide-left-enter-active,
        .slide-left-leave-active {
            transition: transform 0.5s, opacity 0.5s;
        }

        .slide-left-enter,
        .slide-left-leave-to {
            transform: translateX(0px);
            opacity: 1;
        }

        .slide-left-leave-active {
            transform: translateX(-100px);
            opacity: 0;
        }
    }
}
</style> -->

<template>
    <div class="create-post">
        <div class="create-post-header">
            <div class="d-flex">
                <v-icon>mdi-information</v-icon>&nbsp;
                <h5>For sale</h5>
            </div>
        </div>
        <br>
        <div class="create-post-body">



            <form action="/action_page.php">

                <div class="row">
                    <div class="col-3">
                        <span class="float-right mt-1 d-flex">Title<span class="text-red">*</span></span>
                    </div>
                    <div class="col-9">

                        <input type="text" class="text-input form-control" id="validationServer01" required>
                        <div class="valid-feedback">
                            Looks good!
                        </div>

                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1 d-flex">Type<span class="text-red">*</span></span>
                    </div>
                    <div class="col-9">


                        <select class="form-select select-input" id="validationServer04"
                            aria-describedby="validationServer04Feedback" required>
                            <option selected disabled value="">Choose one</option>
                            <option value="volvo">Apartment</option>
                            <option value="saab">Condo</option>
                            <option value="fiat">Mini Condo</option>
                            <option value="audi">Industrial zone</option>
                        </select>
                        <div id="validationServer04Feedback" class="invalid-feedback">
                            Please select a valid state.
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1">Floor</span>
                    </div>
                    <div class="col-9">


                        <select class="form-select select-input" id="validationServer04"
                            aria-describedby="validationServer04Feedback" required>
                            <option selected disabled value="">Choose one</option>
                            <option value="volvo">floor1</option>
                            <option value="saab">floor2</option>
                            <option value="fiat">floor3</option>
                            <option value="audi">floor4</option>
                        </select>
                        <div id="validationServer04Feedback" class="invalid-feedback">
                            Please select a valid state.
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1">Finish State</span>
                    </div>
                    <div class="col-9 mt-1">
                        <div>
                            <input type="radio" class="form-check-input" id="Complete" name="finish" required>
                            <label class="ms-1 form-check-label" for="Complete">Complete</label>
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1">Currency</span>
                    </div>
                    <div class="col-9 mt-1">
                        <div class="d-flex align-center justify-space-between">
                            <div>
                                <input type="radio" class="form-check-input" id="validationFormCheck1" name="currency"
                                    required>
                                <label class="ms-1 form-check-label" for="validationFormCheck1">Myanmar (Kyats)</label>
                            </div>
                            <div>
                                <input type="radio" class="form-check-input" id="validationFormCheck2" name="currency"
                                    required>
                                <label class="ms-1 form-check-label" for="validationFormCheck2">Thai (baht)</label>
                            </div>
                            <div>
                                <input type="radio" class="form-check-input" id="validationFormCheck3" name="currency"
                                    required>
                                <label class="ms-1 form-check-label" for="validationFormCheck3">Us (Dollar)</label>
                                <div class="invalid-feedback">More example invalid feedback text</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1 d-flex">Price kyat(Lakh)<span class="text-red">*</span></span>

                    </div>
                    <div class="col-9">

                        <input type="text" class="text-input form-control" id="validationServer01" required>
                        <div class="valid-feedback">
                            Looks good!
                        </div>

                    </div>
                </div>


                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1">Price Per Sqrt Ft</span>
                    </div>
                    <div class="col-9">

                        <input type="text" placeholder="optional" class="text-input form-control"
                            id="validationServer01" required>
                        <div class="valid-feedback">
                            Looks good!
                        </div>

                    </div>
                </div>


                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1">Price Per Acre</span>
                    </div>
                    <div class="col-9">

                        <input type="text" placeholder="optional" class="text-input form-control"
                            id="validationServer01" required>
                        <div class="valid-feedback">
                            Looks good!
                        </div>

                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1">Bank Installment</span>
                    </div>
                    <div class="col-9 mt-1">
                        <div class="d-flex align-center ">
                            <div class="me-4">
                                <input type="radio" class="form-check-input" id="bankinstallmentNo" name="bank"
                                    required>
                                <label class="ms-1 form-check-label" for="bankinstallmentNo">No</label>
                            </div>
                            <div>
                                <input type="radio" class="form-check-input" id="bankinstallmentYes" name="bank"
                                    required>
                                <label class="ms-1 form-check-label" for="bankinstallmentYes">Yes</label>
                                <div class="invalid-feedback">More example invalid feedback text</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1">Area Type</span>
                    </div>
                    <div class="col-9 d-flex">

                        <input type="text" placeholder="width" class="text-input form-control" id="width" required>
                        &nbsp;&nbsp;
                        <input type="text" placeholder="height" class="text-input form-control" id="height" required>


                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1 d-flex">Furnished or not <span class="text-red">*</span></span>
                    </div>
                    <div class="col-9">
                        <select class="form-select select-input" id="validationServer04"
                            aria-describedby="validationServer04Feedback" required>
                            <option selected disabled value="volov">Choose one</option>
                            <option value="volvo">No furnished</option>
                            <option value="saab">Half furnished</option>
                            <option value="fiat">Full furnished</option>
                        </select>
                        <div id="validationServer04Feedback" class="invalid-feedback">
                            Please select a valid state.
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1 d-flex">Bedroom</span>
                    </div>
                    <div class="col-9">
                        <select class="form-select select-input" id="validationServer04"
                            aria-describedby="validationServer04Feedback" required>
                            <option selected disabled value="volov">Choose one</option>
                            <option value="volvo">1 room</option>
                            <option value="volvo">2 room</option>
                            <option value="volvo">4 room</option>
                            <option value="volvo">5 room</option>
                            <option value="volvo">6 room</option>
                            <option value="volvo">7 room</option>
                            <option value="volvo">8 room</option>
                            <option value="volvo">9 room</option>
                            <option value="volvo">10 room</option>
                        </select>
                        <div id="validationServer04Feedback" class="invalid-feedback">
                            Please select a valid state.
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1 d-flex">Bathroom</span>
                    </div>
                    <div class="col-9">
                        <select class="form-select select-input" id="validationServer04"
                            aria-describedby="validationServer04Feedback" required>
                            <option selected disabled value="volov">Choose one</option>
                            <option value="volvo">1 room</option>
                            <option value="volvo">2 room</option>
                            <option value="volvo">4 room</option>
                            <option value="volvo">5 room</option>
                            <option value="volvo">6 room</option>
                            <option value="volvo">7 room</option>
                            <option value="volvo">8 room</option>
                            <option value="volvo">9 room</option>
                            <option value="volvo">10 room</option>
                        </select>
                        <div id="validationServer04Feedback" class="invalid-feedback">
                            Please select a valid state.
                        </div>
                    </div>
                </div>


                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1 d-flex">Region,state<span class="text-red">*</span></span>
                    </div>
                    <div class="col-9">
                        <select class="form-select select-input" id="validationServer04"
                            aria-describedby="validationServer04Feedback" required>
                            <option selected disabled value="volov">Choose one</option>
                            <option value="volvo">Yangon</option>
                            <option value="volvo">Naypyitaw</option>
                            <option value="volvo">Mandalay</option>
                            <option value="volvo">Ayawady</option>
                            <option value="volvo">Pago</option>
                            <option value="volvo">Shan state</option>
                            <option value="volvo">Mon state</option>

                        </select>
                        <div id="validationServer04Feedback" class="invalid-feedback">
                            Please select a valid state.
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1 d-flex">Township<span class="text-red">*</span></span>
                    </div>
                    <div class="col-9">
                        <select class="form-select select-input" id="validationServer04"
                            aria-describedby="validationServer04Feedback" required>
                            <option selected disabled value="volov">Choose one</option>
                            <option value="volvo">Yangon</option>
                            <option value="volvo">Naypyitaw</option>
                            <option value="volvo">Mandalay</option>
                            <option value="volvo">Ayawady</option>
                            <option value="volvo">Pago</option>
                            <option value="volvo">Shan state</option>
                            <option value="volvo">Mon state</option>

                        </select>
                        <div id="validationServer04Feedback" class="invalid-feedback">
                            Please select a valid state.
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1 d-flex">Contact,Phone<span class="text-red">*</span></span>
                    </div>
                    <div class="col-9">

                        <input type="number" class="text-input form-control" id="validationServer01" required>
                        <div class="valid-feedback">
                            Looks good!
                        </div>

                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1">Advertiser <span class="text-red">*</span> </span>
                    </div>
                    <div class="col-9 mt-1">
                        <div class="d-flex align-center ">
                            <div class="me-4">
                                <input type="radio" class="form-check-input" id="agent" name="Advertiser" required>
                                <label class="ms-1 form-check-label" for="agent">Agent</label>
                            </div>
                            <div>
                                <input type="radio" class="form-check-input" id="owner" name="Advertiser" required>
                                <label class="ms-1 form-check-label" for="owner">Owner</label>
                                <div class="invalid-feedback">More example invalid feedback text</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1 d-flex">Description<span class="text-red">*</span></span>
                    </div>
                    <div class="col-9">

                        <div>

                            <textarea class="text-input form-control" id="exampleFormControlTextarea1"
                                rows="3"></textarea>
                        </div>

                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1">Connect project</span>
                    </div>
                    <div class="col-9 mt-1">
                        <div class="d-flex align-center ">
                            <div class="me-4">
                                <input type="radio" class="form-check-input" id="projectno" name="project" required>
                                <label class="ms-1 form-check-label" for="projectno">No</label>
                            </div>
                            <div>
                                <input type="radio" class="form-check-input" id="projectyes" name="project" required>
                                <label class="ms-1 form-check-label" for="projectyes">Yes</label>
                                <div class="invalid-feedback">More example invalid feedback text</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-3">
                        <span class="float-right mt-1">Connect Map</span>
                    </div>
                    <div class="col-9 mt-1">
                        <div class="d-flex align-center ">
                            <div class="me-4">
                                <input type="radio" class="form-check-input" id="mapno" name="Map" required>
                                <label class="ms-1 form-check-label" for="mapno">No</label>
                            </div>
                            <div>
                                <input type="radio" class="form-check-input" id="mapyes" name="Map" required>
                                <label class="ms-1 form-check-label" for="mapyes">Yes</label>
                                <div class="invalid-feedback">More example invalid feedback text</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row-12 d-flex">
                    <v-btn elevation="20" class="mt-3 submit-btn ms-auto" type="submit">Submit</v-btn>

                </div>

            </form>




        </div>
    </div>
</template>

<script>
export default {
    name: 'create_post',

    data() {
        return {
            selectedType: '',
        }
    },

    watch: {
        selectedType(newValue) {
            if (newValue === 'Condo') {
                // Reset phone number when 'Condo' is selected
                this.phone.value = '';
            }
        }
    }
}
</script>

<style>
.create-post {
    overflow: hidden;
    width: 100%;
    height: auto;
    padding: 20px 30px;
    background-color: #fff;


    .create-post-header {
        width: 100%;
        height: auto;
        padding: 10px 0px;
        display: flex;
        justify-content: center;
        background-color: #D9EDF7;
        border-radius: 10px;
        margin-bottom: 10px;
    }

    .create-post-body {
        border-radius: 10px;
        box-shadow: 0px 4px 20px 2px rgba(0, 0, 0, 0.4);
        padding: 30px;
        background-color: #F5F5F5;

        form {
            width: 80%;
            margin: auto;

            .text-input {
                width: 100%;
                padding: 6px 10px;
                font-size: 16px;
                border-radius: 6px;
                box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.5);

                &:focus {
                    outline: none;
                    box-shadow: inset 0px 0px 6px #e86f52;
                }
            }

            .select-input {
                overflow: hidden;
                width: 100%;
                padding: 6px 10px;
                font-size: 16px;
                border-radius: 6px;
                box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.5);

                &:focus {
                    outline: none;
                    box-shadow: inset 0px 0px 6px #e86f52;
                }
            }

            .form-check-input {
                border: 3px solid #525252;
                width: 20px;
                height: 20px;
                margin-top: 1px;
                transition: all 0.1s ease-in-out;
            }

            .form-check-input:checked {
                border: 3px solid #e86f52;
                box-shadow: none;
                background-color: #e86f52;
            }

            .submit-btn {
                background-color: #e86f52;
                color: #fff;
                border-radius: 30px;
                margin: 0 auto;
            }
        }

        .slide-left-enter-active,
        .slide-left-leave-active {
            transition: transform 0.5s, opacity 0.5s;
        }

        .slide-left-enter,
        .slide-left-leave-to {
            transform: translateX(0px);
            opacity: 1;
        }

        .slide-left-leave-active {
            transform: translateX(-100px);
            opacity: 0;
        }
    }
}
</style>
