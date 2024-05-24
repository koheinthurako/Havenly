<template>

    <div class="all-post-view">
        <v-container>
            <div class="row px-5">
                <div class="col-md-8 col-sm-12 left">
                    <div class="left">
                        <div class="header">
                            <h3>See all post of {{ getName }} </h3>

                        </div>
                        <div class="display-posts">
                            <!-- display temp page -->
                            <temp-page :key="tempPageKey" :data="tempPageData"></temp-page>

                        </div>

                    </div>
                </div>
                <div class="col-md-4 d-none d-sm-block right h-100">
                    <div class="right">
                        <div class="filter-search" :class="{ 'scrolled': isScrolled }">
                            <h5 class="header">Search filter</h5><br><br>
                            <div class="body">
                                <v-radio-group v-model="setType" inline class="p-0">
                                    <v-radio color="orange" label="Sell Posts" value="sell"></v-radio>
                                    <v-spacer></v-spacer>
                                    <v-radio color="orange" label="Rent Posts" value="rent"></v-radio>
                                </v-radio-group>
                                <hr class="pb-3 m-0 mx-4">
                                <v-select v-model="selectedType" bg-color="#EDEDED" filled variant="outlined"
                                    density="compact" rounded="lg" clearable label="Select type"
                                    :items="['Condo', 'Apartment', 'House', 'Office']"></v-select>
                                <v-select v-model="selectedRoom" bg-color="#EDEDED" filled variant="outlined"
                                    density="compact" rounded="lg" clearable label="Select Room"
                                    :items="['Single room', 'Double room']"></v-select>
                                <v-select v-model="selectedArea" bg-color="#EDEDED" filled variant="outlined"
                                    density="compact" rounded="lg" clearable label="Select Square feet"
                                    :items="['under 600', 'between 600 and 1000', 'between 1000 and 1500', 'between 1500 and 2000', 'above 2400']"></v-select>
                                <hr class="pb-3 m-0 mx-4">
                                <div class="d-flex justify-space-between">
                                    <v-btn class="all-btn" @click="getAllFromTempPage"><v-icon
                                            class="me-1">mdi-all-inclusive</v-icon>All
                                        posts</v-btn>
                                    <v-btn class="search-btn"
                                        @click="sendDataToTempPage"><v-icon>mdi-magnify</v-icon>Search</v-btn>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>
            </div>

        </v-container>
    </div>

</template>

<script>
import AES from 'crypto-js/aes'
import Utf8 from 'crypto-js/enc-utf8'

import tempPage from '@/views/TempCollection/TempForAllPostView.vue'

export default {
    name: 'AllPostView',


    data() {
        return {
            // get datas
            setType: 'sell',
            selectedArea: '',
            selectedType: '',
            selectedRoom: '',
            tempPageData: null,
            tempPageKey: 0,
            isScrolled: false,
            getName: '',
            getPostType: null,

        }
    },

    components: {
        tempPage,
    },

    mounted() {

        this.getPostType = sessionStorage.getItem('getPostType');
        if (!this.getPostType) {
            console.error('No postType found in sessionStorage');
        } else {
            this.changeName(this.getPostType);

            const data = {
                setPost: '',
                postType: this.changeName(this.getPostType),
                area: '',
                room: '',
            };
            this.tempPageData = data;
            this.tempPageKey += 1;

        }

        window.addEventListener('scroll', this.handleScroll);
    },

    beforeUnmount() {
        window.removeEventListener('scroll', this.handleScroll);
    },

    methods: {

        handleScroll() {
            this.isScrolled = window.scrollY >= 15;
            console.log(this.isScrolled);
        },

        encryptId(id) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
            const encryptedId = AES.encrypt(id.toString(), secretKey).toString()
            return encryptedId
        },

        decryptId(encryptedId) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(encryptedId, secretKey);
            const decryptedId = decryptedBytes.toString(Utf8);
            return decryptedId; // Return the decrypted string directly
        },

        encryptData(data) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
            const encryptedId = AES.encrypt(data, secretKey).toString()
            return encryptedId
        },

        decryptData(data) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(data, secretKey);
            const decryptedData = decryptedBytes.toString(Utf8);
            return decryptedData;
        },

        changeName(get) {
            this.getName = this.decryptData(get);
            return this.getName;
        },

        mapAreaValue(selectedArea) {
            switch (selectedArea) {
                case 'under 600':
                    return { min: 0, max: 600 };
                case 'between 600 and 1000':
                    return { min: 600, max: 1000 };
                case 'between 1000 and 1500':
                    return { min: 1000, max: 1500 };
                case 'between 1500 and 2000':
                    return { min: 1500, max: 2000 };
                case 'above 2400':
                    return { min: 2400, max: Infinity };
                default:
                    return null;
            }
        },

        sendDataToTempPage() {
            if (this.selectedType) {
                this.changeName(this.encryptData(this.selectedType));
            }


            const data = {
                setPost: this.setType,
                postType: this.selectedType,
                area: this.selectedArea,
                room: this.selectedRoom,
            };
            this.tempPageData = data;
            this.tempPageKey += 1;

        },

        getAllFromTempPage() {

            this.getPostType = sessionStorage.getItem('getPostType');
            if (this.getPostType) {
                this.changeName(this.getPostType);
            }

            // first clean the fields
            this.selectedType = '';
            this.selectedArea = '';
            this.selectedRoom = '';

            const data = {
                setPost: '',
                postType: '',
                area: '',
                room: '',
            };
            this.tempPageData = data;
            this.tempPageKey += 1;

        }
    }
}


</script>

<style>
.scrolled {
    position: fixed !important;
    top: 12%;
    width: 340px;
    box-shadow: 0px 6px 20px 1px rgba(0, 0, 0, 0.4);
    z-index: 1;
}
</style>