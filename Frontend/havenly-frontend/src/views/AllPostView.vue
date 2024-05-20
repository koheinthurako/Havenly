<template>

    <div class="all-post-view">
        <v-container>
            <div class="row">
                <div class="col-md-8 col-sm-12">
                    <div class="left">
                        <div class="header">
                            <h4>See all post of {{ data }} </h4>
                            <v-icon>mdi-chevron-double-right</v-icon>

                            <div class="display-posts">
                                <!-- <TempAllPost /> -->
                                hello world
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 d-none d-sm-block">
                    <div class="right">
                        hello world right
                    </div>
                </div>
            </div>
        </v-container>
    </div>

</template>

<script>
import AES from 'crypto-js/aes'
import Utf8 from 'crypto-js/enc-utf8'

// import TempAllPost from '@/views/TempCollection/TempForAllPostView.vue'

export default {
    name: 'AllPostView',


    data() {
        return {
            data: '',
            getPostType: null,
        }
    },

    components: {
        // TempAllPost,
    },

    mounted() {

        this.getPostType = sessionStorage.getItem('getPostType');
        if (!this.getPostType) {
            console.error('No postType found in sessionStorage');
        } else {
            this.changeName(this.getPostType);
        }
    },

    methods: {
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
            this.data = this.decryptData(get);
            return this.data;
        }
    }
}


</script>
