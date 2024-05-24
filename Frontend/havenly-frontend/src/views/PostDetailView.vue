<template>

    <div class="post-detail-view-page">

        <!-- bootstrap container is getting errro ;( -->
        <v-container>
            <div class="row ">

                <div class="col-md-8 ">
                    <div class="left">
                        <div class="col-md-12">
                            <div class="header">
                                <div class="row">
                                    <div class="col-md-8 col-sm-12">
                                        <h3 class="color-brick">{{ post.title }}</h3>
                                        <div class="d-flex"><v-icon>mdi-map-marker</v-icon>
                                            <p>{{ post.province }} / {{
                                                post.region }} / {{
                                                    post.country }}</p>
                                        </div>
                                    </div>
                                    <div class="col-md-4 d-none d-sm-block">
                                        <div class="d-flex"><v-icon>mdi-office-building</v-icon>
                                            <p>{{ post.property_type }}</p>
                                        </div>
                                        <div class="d-flex"><v-icon
                                                class="me-1">mdi-format-list-bulleted-type</v-icon><span>For
                                                {{ this.getPostType(post.post_id) }}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <hr class="mx-auto">


                        <div class=" images">
                            <div class="row">
                                <div class="col-md-8 col-sm-12 mb-2">
                                    <v-img :src="post.photo_url[0]" class="img-1 m-auto"></v-img>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div class="row">
                                        <div class="col-6 col-md-12">
                                            <v-img :src="post.photo_url[1]" class="img-2"></v-img>
                                        </div>
                                        <div class="col-6 col-md-12 mt-md-3 show-overlay" @click="sheet = !sheet"
                                            style="cursor:pointer;">
                                            <div class="img-overlay">
                                                <div class="see-first">
                                                    <div class="d-flex">
                                                        <v-icon>mdi-image-multiple</v-icon>
                                                        <h5>More images</h5>
                                                    </div>
                                                </div>
                                            </div>
                                            <v-img
                                                :src="post.photo_url[2] != null ? post.photo_url[2] : post.photo_url[1]"
                                                class="img-2"></v-img>
                                        </div>
                                    </div>

                                </div>
                            </div>

                        </div>

                        <hr class="mx-auto">



                        <div class="col-md-12 ">
                            <div class="description">
                                <h5 class="color-brick">ပို့စ် အကြောင်းအရာများ</h5>

                                <p class="detail-text">{{ post.description }}</p>
                            </div>


                        </div>

                        <v-btn class="req-btn" @click="openDialog">
                            Request more
                        </v-btn>

                        <hr class="mx-auto">


                        <div class="col-md-12">
                            <h5 class="color-brick mb-4">ပို့စ် အသေးစိတ်အချက်များ</h5>

                            <div class="row p-0">
                                <div class="col-6 ">
                                    <div class="d-flex"><v-icon>mdi-map-marker</v-icon>
                                        <p>{{ post.province }} / {{
                                            post.region }} / {{
                                                post.country }}</p>
                                    </div>
                                    <div class="d-flex"><v-icon>mdi-office-building</v-icon>
                                        <p>{{ post.property_type }}</p>
                                    </div>
                                    <div class="d-flex"><v-icon>mdi-bed</v-icon>
                                        <p>အိပ်ခန်း 2 ခန်း</p>
                                    </div>
                                    <div class="d-flex"><v-icon>mdi-arrow-expand-all</v-icon>
                                        <p>{{ post.area }} Square Ft </p>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="d-flex"><v-icon>mdi-checkbox-marked-circle</v-icon>
                                        <p>ဆောက်လုပ်ပြီး</p>
                                    </div>



                                    <div class="d-flex"><v-icon>mdi-tag-multiple</v-icon>
                                        <p>စျေးနှုန်း</p><br>

                                    </div>
                                    <div class="d-flex">

                                        <p class="price"> {{ post.price }}</p>
                                        <span class="mt-1 ms-2">Kyats</span>
                                    </div>

                                </div>
                            </div>
                        </div>


                        <hr class="mx-auto">



                        <v-dialog v-model="reqDialog" width="450" transition="dialog-top-transition">

                            <v-card class="request-detail-dialog">
                                <div class="p-2">
                                    <h5 class="text-center">Make enquiry</h5>
                                    <hr class="mx-auto">
                                    <div class="d-flex">

                                        <v-btn class="half-btn w-50">{{ post.property_type }}</v-btn>
                                        <v-btn class="half-btn w-50 bg-green">{{
                                            this.getPostType(post.post_id) }}</v-btn>

                                    </div>
                                    <hr class="mx-auto">
                                    <v-text-field density="compact" rounded="lg" variant="solo" v-model="name"
                                        label="Name *" required class="m-0"></v-text-field>
                                    <v-text-field density="compact" rounded="lg" variant="solo" v-model="gmail"
                                        label="Gmail *" required></v-text-field>
                                    <v-text-field density="compact" focused v-model="phoneNumber"
                                        :prefix="selectedCountry.code" variant="solo" label="Phone Number">
                                        <template v-slot:prepend-inner>
                                            <img :src="selectedCountry.flag" alt="flag" class="me-2"
                                                style="height: 24px;" />
                                        </template>
                                    </v-text-field>
                                    <v-textarea variant="solo" label="Label"></v-textarea>
                                    <v-btn class="request-btn w-100 bg-danger text-light">
                                        Request Details
                                    </v-btn>
                                </div>
                            </v-card>

                        </v-dialog>

                        <v-bottom-sheet v-model="sheet" inset>
                            <v-card class="text-center">
                                <v-card-text class="p-0 m-0">
                                    <div class="d-flex justify-space-between py-2 px-4">
                                        <h4 style="color:#e86f52;">More images</h4>
                                        <v-btn icon color="#e86f52" variant="elevated" @click="sheet = !sheet">
                                            <v-icon>mdi-close-circle-outline</v-icon>
                                        </v-btn>
                                    </div>
                                    <br>

                                    <v-card style="background-color:transparent; box-shadow: none;">
                                        <v-card-text>

                                            <v-carousel hide-delimiters class="testing-edit">
                                                <template v-slot:prev="{ props }">
                                                    <v-btn icon color="#e86f52" variant="elevated"
                                                        @click="props.onClick">
                                                        <v-icon>mdi-chevron-left</v-icon>
                                                    </v-btn>
                                                </template>
                                                <template v-slot:next="{ props }">
                                                    <v-btn icon color="#e86f52" variant="elevated"
                                                        @click="props.onClick"><v-icon>mdi-chevron-right</v-icon></v-btn>
                                                </template>
                                                <v-carousel-item v-for="(image, index) in post.photo_url" :key="index"
                                                    :src="image" cover>
                                                </v-carousel-item>
                                            </v-carousel>

                                        </v-card-text>
                                    </v-card>
                                </v-card-text>
                            </v-card>
                        </v-bottom-sheet>

                        <div class="function_btn">
                            <div class=row>
                                <div class="px-0 mb-3 col-md-6 col-12 d-flex justify-md-end justify-center">
                                    <v-btn class="share-btn "><v-icon
                                            class="me-1">mdi-thumb-up-outline</v-icon>Like</v-btn>

                                    <v-btn class="fav-btn "><v-icon class="me-1">mdi-heart-outline</v-icon>Add to
                                        favorite</v-btn>
                                </div>
                                <div class="px-0 col-md-6 col-12 d-flex justify-md-start justify-center mb-3">
                                    <v-btn class="compare-btn"><v-icon class="me-1">mdi-phone</v-icon>Contact
                                        Owner</v-btn>
                                    <v-btn class="report-btn "><v-icon
                                            class="me-1">mdi-flag-outline</v-icon>Report</v-btn>
                                </div>
                            </div>
                        </div>

                        <hr class="mx-auto d-block d-sm-none mt-0">
                    </div>
                </div>






                <div class="col-md-4">
                    <div class="right">
                        <div class="search-bar ">
                            <input type="text" id="fname" name="fname" placeholder="Search here">
                            <v-btn class="px-4">Search</v-btn>
                        </div>

                        <v-card class="request-detail mb-3">
                            <h5 class="header">Make enquiry</h5>
                            <div class="p-2">

                                <br><br>


                                <div class="d-flex mt-2">

                                    <v-btn class="half-btn w-50">{{ post.property_type }}</v-btn>
                                    <v-btn class="half-btn w-50 bg-green">{{ this.getPostType(post.post_id) }}</v-btn>

                                </div>
                                <hr class="mx-auto">
                                <v-text-field density="compact" rounded="lg" variant="solo" v-model="name"
                                    label="Name *" required class="m-0"></v-text-field>
                                <v-text-field density="compact" rounded="lg" variant="solo" v-model="gmail"
                                    label="Gmail *" required></v-text-field>
                                <v-text-field density="compact" focused v-model="phoneNumber"
                                    :prefix="selectedCountry.code" variant="solo" label="Phone Number">
                                    <template v-slot:prepend-inner>
                                        <img :src="selectedCountry.flag" alt="flag" class="me-2"
                                            style="height: 24px;" />
                                    </template>
                                </v-text-field>
                                <v-textarea variant="solo" label="Label"></v-textarea>
                                <v-btn class="request-btn w-100">
                                    <v-icon class="final-icon">mdi-email</v-icon>Request Details
                                </v-btn>
                            </div>
                        </v-card>

                        <v-card class="popular-posts mb-3">
                            <h5 class="header">
                                <v-icon>mdi-star</v-icon>
                                popular {{ post.property_type }}
                            </h5>
                            <br><br>

                            <div class="p-2 mt-2">

                                <div class="post-icon mb-3 row" v-for="data in Sells" :key="data">
                                    <div class="pi-overlay"></div>
                                    <div class="col-3 p-0">
                                        <v-img :src="data.img" class="w-auto h-100"></v-img>

                                    </div>
                                    <div class="col-9">

                                        <p class="p-0">{{ truncateText(post.title, 29) }}</p>

                                        <div class="d-flex py-0" style="margin-top: -10px;">
                                            <v-icon>mdi-map-marker</v-icon>
                                            <!-- <p class="m-0">Bangkok</p> -->
                                            <p class="m-0">{{ post.province }} / {{
                                                post.country }}</p>
                                        </div>
                                    </div>


                                </div>

                            </div>
                        </v-card>
                    </div>


                </div>


            </div>


            <div class="row px-5">
                <hr class="mx-auto">

                <div class="related_section">
                    <div class="w-100">
                        <h3 class="header-text">Related Sell posts</h3>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-12" v-for="data in Sells" :key="data.id">

                            <div class="post">
                                <div class="img">
                                    <div class="type">
                                        <div>
                                            <div><v-icon>mdi-format-list-bulleted-type</v-icon>{{ data.post_type }}
                                            </div>
                                        </div>
                                        <div>

                                            <div @click="toggleSaved(data.id)">
                                                <v-icon v-if="isSaved(data.id)">mdi-bookmark-check</v-icon>
                                                <v-icon v-else>mdi-bookmark-outline</v-icon>
                                                Save Post
                                            </div>

                                        </div>
                                    </div>
                                    <v-img :src="data.img" />
                                    <div class="info">
                                        <div class="d-flex"><v-icon>mdi-eye</v-icon><span>441</span></div>
                                        <div><v-icon>mdi-currency-usd</v-icon>
                                            <span class="money">2300 Lakh</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="data">
                                    <div class="header">
                                        <h5>{{ data.title }}</h5>
                                        <p>{{ truncateText(post.title, 30) }}</p>
                                    </div>
                                    <v-divider inset class="mx-auto py-0 mt-0"></v-divider>

                                    <div class="information">
                                        <div><v-icon>mdi-map-marker</v-icon> Insein Yangon</div>
                                        <div><v-icon>mdi-post</v-icon><span>For {{ data.type }}</span></div>
                                    </div>


                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="footer_section">
                        <v-btn class="footer-btn">See all
                            posts<v-icon>mdi-chevron-double-right</v-icon></v-btn>
                    </div>
                </div>


                <hr class="mx-auto">


                <div class="related_section">
                    <div class="w-100">
                        <h3 class="header-text">Related posts in this area</h3>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-12" v-for="data in Sells" :key="data.id">

                            <div class="post">
                                <div class="img">
                                    <div class="type">
                                        <div>
                                            <div><v-icon>mdi-format-list-bulleted-type</v-icon>{{ data.post_type }}
                                            </div>
                                        </div>
                                        <div>

                                            <div @click="toggleSaved(data.id)">
                                                <v-icon v-if="isSaved(data.id)">mdi-bookmark-check</v-icon>
                                                <v-icon v-else>mdi-bookmark-outline</v-icon>
                                                Save Post
                                            </div>

                                        </div>
                                    </div>
                                    <v-img :src="data.img" />
                                    <div class="info">
                                        <div class="d-flex"><v-icon>mdi-eye</v-icon><span>441</span></div>
                                        <div><v-icon>mdi-currency-usd</v-icon>
                                            <span class="money">2300 Lakh</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="data">
                                    <div class="header">
                                        <h5>{{ data.title }}</h5>
                                        <p>{{ truncateText(post.description, 30) }}</p>
                                    </div>
                                    <v-divider inset class="mx-auto py-0 mt-0"></v-divider>

                                    <div class="information">
                                        <div><v-icon>mdi-map-marker</v-icon> Insein Yangon</div>
                                        <div><v-icon>mdi-post</v-icon><span>For {{ data.type }}</span></div>
                                    </div>


                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="footer_section">
                        <v-btn class="footer-btn">See all
                            posts<v-icon>mdi-chevron-double-right</v-icon></v-btn>
                    </div>
                </div>



            </div>

        </v-container>
    </div>
</template>

<script>
import AES from 'crypto-js/aes'
import Utf8 from 'crypto-js/enc-utf8';

export default {

    name: 'postDetailView',

    data: () => ({
        postGetId: null,

        imageDialog: false,
        sheet: false,
        Sells: [
            { id: 1, title: 'Hello world', img: require('@/assets/img/h1.jpg'), post_type: 'Condo', type: 'sell' },
            { id: 2, title: 'Hello Again', img: require('@/assets/img/h3.jpg'), post_type: 'Apartment', type: 'sell' },
            { id: 3, title: 'Hello Elephant', img: require('@/assets/img/h5.jpg'), post_type: 'Industrial', type: 'sell' },

        ],
        savedPosts: [],

        // to keep all datas
        post: {
            province: '',
            region: '',
            country: '',
            latitude: '',
            longitude: '',
            post_id: '',
            title: '',
            description: '',
            property_type: '',
            area: '',
            price: '',
            photo_url: [],
            deposit: '',
            least_contract: ''
        },

        reqDialog: false,
        phoneNumber: '',
        selectedCountry: {
            code: '+95',
            flag: 'https://flagcdn.com/w320/mm.png' // Myanmar flag
        },


        img: require("@/assets/img/1.jpg"),
        img1: require("@/assets/img/2.jpg"),
        img2: require("@/assets/img/3.jpg")
    }),

    mounted() {
        this.fetchPost();
    },
    methods: {

        getPostType(data) {
            const typeLetter = data.charAt(0);
            if (typeLetter === 's') {
                return "Sell";
            } else {
                return "Rent";
            }
        },

        splitData(data) {
            return data.split(' ')[0];
        },

        truncateText(text, charLimit) {
            if (text.length > charLimit) {
                return text.slice(0, charLimit) + '...';
            }
            return text;
        },


        openImageDialog() {
            this.imageDialog = true;
        },
        closeImageDialog() {
            this.imageDialog = false;
        },
        toggleSaved(postId) {
            const index = this.savedPosts.indexOf(postId);
            if (index !== -1) {
                this.savedPosts.splice(index, 1);
            } else {
                this.savedPosts.push(postId);
            }
        },
        isSaved(postId) {
            return this.savedPosts.includes(postId);
        },

        // encryptId(id) {
        //     const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
        //     const encryptedId = AES.encrypt(id.toString(), secretKey).toString()
        //     return encryptedId
        // },

        decryptId(encryptedId) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(encryptedId, secretKey);
            const decryptedId = decryptedBytes.toString(Utf8);
            return parseInt(decryptedId, 10);
        },

        decryptData(encryptedId) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(encryptedId, secretKey);
            const decryptData = decryptedBytes.toString(Utf8);
            return decryptData;
        },
        openDialog() {
            this.reqDialog = true;
        },

        closeDialog() {
            this.reqDialog = false;
        },

        // async fetchPostData() {
        //     const postId = this.splitData(this.$route.params.id);
        //     const decryptData = this.decryptData(postId);
        //     try {

        //         const response = await axios.get(`http://localhost:8083/posts/getPostById/${decryptData}`);
        //         this.post = response.data;
        //         console.log("all show post ", this.post);
        //     } catch (error) {
        //         console.error('Error fetching post:', error);
        //     }
        // }

        async fetchPost() {
            const postId = this.splitData(this.$route.params.id);
            const decryptData = this.decryptData(postId);
            try {
                const response = await fetch(`http://localhost:8083/posts/getPostById/${decryptData}`);

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                // Parse the response as JSON
                const data = await response.json();

                // Check the post type
                if (data.testsellpostss) {
                    this.processPostData(data.testsellpostss);
                } else if (data.testrentposts) {
                    this.processPostData(data.testrentposts);
                } else {
                    console.error('Unexpected data format:', data);
                }
            } catch (error) {
                console.error('Error fetching post:', error);
            }
        },
        processPostData(postData) {
            const imageUrls = Array.isArray(postData.image) ? postData.image : [postData.image];
            this.post = {
                province: postData.locations.province,
                region: postData.locations.region,
                country: postData.locations.countries.country_name,
                latitude: postData.locations.latitude,
                longitude: postData.locations.longitude,
                post_id: postData.sell_post_id,
                title: postData.title,
                description: postData.description,
                property_type: postData.property_type,
                area: postData.area,
                price: postData.price,
                photo_url: imageUrls,
                deposit: postData.deposit || '',
                least_contract: postData.least_contract || ''
            };
        },



    },

}
</script>


<style lang="scss">
.v-bottom-sheet {
    border-radius: 10px !important;
    padding-bottom: 20px;

    .v-img {
        border-radius: 10px;
    }
}
</style>