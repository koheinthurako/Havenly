<template>

    <!-- <v-container v-if="data">
        <v-row>
            <v-col>
                <p>SetType: {{ data.setPost }}</p>
                <p>PostType: {{ data.postType }}</p>
                <div v-if="data.area">
                    <p>Count: {{ processString(data.area)[0] }}</p>
                    <div v-if="processString(data.area)[0] < 3">

                        <p>first: {{ processString(data.area)[3] }}</p>
                        <p>Last: {{ processString(data.area)[4] }}</p>
                    </div>
                    <div v-else>
                        <p>First: {{ processString(data.area)[1] }}</p>
                        <p>Second: {{ processString(data.area)[2] }}</p>
                        <p>Third: {{ processString(data.area)[3] }}</p>
                        <p>Last: {{ processString(data.area)[4] }}</p>
                    </div>
                </div>

                <p>Room: {{ data.room }}</p>
            </v-col>
        </v-row>
    </v-container> -->

    <div class="view-posts row p-0 mb-3 mt-2" v-for="item in paginatedItems" :key="item.id">
        <div class="col-6 left p-0 m-0">
            <v-img :src="item.img" />
            <div class="show-icon">
                <div class="sell d-flex"><v-icon class="me-2">mdi-format-list-bulleted-type</v-icon>
                    <span>{{ item.settype }}&nbsp;post</span>
                </div>
                <v-btn class="like-btn"><v-icon class="me-2">mdi-thumb-up-outline</v-icon><span>Like</span></v-btn>
            </div>
        </div>
        <div class="col-6 p-2 right">
            <h5 class="ps-1">{{ truncateText(item.title, 30) }}</h5>
            <hr class="mx-2 my-3">
            <div class="types">
                <div><v-icon class="me-1">mdi-map-marker-radius</v-icon><span>Region / province / country</span>
                </div>
                <div><v-icon class="me-1">mdi-office-building</v-icon><span>{{ item.posttype }}</span></div>
                <div class="row p-0">
                    <div class="col-6">
                        <div class="d-flex"><v-icon class="me-1">mdi-bed-king</v-icon><span>{{ item.postroom }}
                            </span></div>
                        <div class="d-flex"><v-icon class="me-1"
                                style="transform:rotate(45deg);">mdi-arrow-all</v-icon><span>{{
                                    item.area }} ft
                            </span></div>
                    </div>
                    <div class="col-6">
                        <v-btn class="direct-btn" readonly><v-icon>mdi-check-circle</v-icon>direct owner</v-btn>
                    </div>
                </div>
            </div>
            <hr class="mx-2 my-2">
            <div class="footer py-0 my-0">
                <div class="money d-flex"><v-icon>mdi-currency-usd</v-icon><span>{{ item.price }}</span>
                    <p>(kyats)</p>
                </div>
                <v-btn class="detail-btn d-flex">See detail <v-icon
                        class="me-2">mdi-chevron-double-right</v-icon></v-btn>
            </div>
        </div>
    </div>

    <v-pagination active-color="#e86f52" rounded="circle" v-model="currentPage" :length="totalPages"
        @input="onPageChange"></v-pagination>
</template>

<script>
export default {
    props: {
        data: {
            type: Object,
            required: false,
            default: () => null // Default to null to handle initial state
        }
    },

    data() {
        return {
            itemsPerPage: 10,
            currentPage: 1,
            items: [
                { id: 1, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/1.jpg'), settype: 'Sell', posttype: 'Condo', postroom: 'Single room', area: 200, price: '4000' },
                { id: 2, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/2.jpg'), settype: 'Sell', posttype: 'Apartment', postroom: 'Double room', area: 1200, price: '4000' },
                { id: 3, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/3.jpg'), settype: 'Sell', posttype: 'Apartment', postroom: 'Single room', area: 1600, price: '4000' },
                { id: 4, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/4.jpg'), settype: 'Sell', posttype: 'Condo', postroom: 'Double room', area: 1800, price: '5000' },
                { id: 5, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/5.jpg'), settype: 'Rent', posttype: 'Condo', postroom: 'Single room', area: 400, price: '3400' },
                { id: 6, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/6.jpg'), settype: 'Rent', posttype: 'Condo', postroom: 'Single room', area: 2000, price: '2500' },
                { id: 7, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/7.jpg'), settype: 'Rent', posttype: 'House', postroom: 'Single room', area: 2400, price: '9000' },
                { id: 8, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/8.jpg'), settype: 'Rent', posttype: 'House', postroom: 'Double room', area: 3000, price: '1000' },
                { id: 9, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/9.jpg'), settype: 'sell', posttype: 'House', postroom: 'Single room', area: 2400, price: '2000' },
                { id: 10, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/10.jpg'), settype: 'Sell', posttype: 'Condo', postroom: 'Double room', area: 1800, price: '2400' },
                { id: 11, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/11.jpg'), settype: 'Sell', posttype: 'Office', postroom: 'Single room', area: 900, price: '4700' },
                { id: 12, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/21.jpg'), settype: 'Rent', posttype: 'Office', postroom: 'Double room', area: 400, price: '1350' },
                { id: 13, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/8.jpg'), settype: 'Rent', posttype: 'Office', postroom: 'Double room', area: 400, price: '1350' },
                { id: 14, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/9.jpg'), settype: 'Rent', posttype: 'Office', postroom: 'Double room', area: 400, price: '1350' },
                { id: 15, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/6.jpg'), settype: 'Rent', posttype: 'Apartment', postroom: 'Double room', area: 700, price: '4000' },
                { id: 16, title: 'LuxPride by Wallaya Villas', img: require('@/assets/img/7.jpg'), settype: 'Rent', posttype: 'Apartment', postroom: 'Single room', area: 1600, price: '4000' },

            ],

        };
    },
    computed: {
        totalPages() {
            return Math.ceil(this.items.length / this.itemsPerPage);
        },
        //     return this.items.filter(item => {
        //         let matchesSetPost = true;
        //         let matchesPostType = true;
        //         let matchesRoom = true;
        //         let matchesArea = true;

        //         if (this.data) {
        //             if (this.data.setPost) {
        //                 matchesSetPost = item.settype.toLowerCase() === this.data.setPost.toLowerCase();
        //             }
        //             if (this.data.postType) {
        //                 matchesPostType = item.posttype.toLowerCase() === this.data.postType.toLowerCase();
        //             }
        //             if (this.data.room) {
        //                 matchesRoom = item.postroom.toLowerCase() === this.data.room.toLowerCase();
        //             }
        //             if (this.data.area) {
        //                 console.log(this.data.area);
        //                 const { operator, value, min, max } = this.data.area;
        //                 switch (operator) {
        //                     case '<':
        //                         matchesArea = item.area < value;
        //                         break;
        //                     case '>':
        //                         matchesArea = item.area > value;
        //                         break;
        //                     case 'between':
        //                         matchesArea = item.area >= min && item.area <= max;
        //                         break;
        //                     default:
        //                         matchesArea = true;
        //                 }
        //             }
        //         }

        //         return matchesSetPost && matchesPostType && matchesRoom && matchesArea;
        //     });
        // },

        filteredItems() {
            return this.items.filter(item => {
                let matchesSetPost = true;
                let matchesPostType = true;
                let matchesRoom = true;
                let matchesArea = true;

                if (this.data) {
                    if (this.data.setPost) {
                        matchesSetPost = item.settype.toLowerCase() === this.data.setPost.toLowerCase();
                    }
                    if (this.data.postType) {
                        matchesPostType = item.posttype.toLowerCase() === this.data.postType.toLowerCase();
                    }
                    if (this.data.room) {
                        matchesRoom = item.postroom.toLowerCase() === this.data.room.toLowerCase();
                    }
                    if (this.data.area) {

                        if (this.processString(this.data.area)[0] < 3) {
                            // console.log("length 2");
                            // console.log("Get condition", this.processString(this.data.area)[3]);
                            // console.log("Get Max", this.processString(this.data.area)[4]);

                            if (this.processString(this.data.area)[3] === 'above') {
                                matchesArea = item.area >= this.processString(this.data.area)[4];
                            } else {
                                matchesArea = item.area <= this.processString(this.data.area)[4];
                            }
                        } else {
                            // console.log("length 4");
                            // console.log("Get condition : ", this.processString(this.data.area)[1]);
                            // console.log("Get Lower ", this.processString(this.data.area)[2]);
                            // console.log("Get Max ", this.processString(this.data.area)[4]);

                            matchesArea = this.processString(this.data.area)[2] < item.area && item.area < this.processString(this.data.area)[4];
                        }

                    }
                }

                // console.log(`matchesSetPost: ${matchesSetPost}, matchesPostType: ${matchesPostType}, matchesRoom: ${matchesRoom}, matchesArea: ${matchesArea}`);
                return matchesSetPost && matchesPostType && matchesRoom && matchesArea;
            });
        },

        paginatedItems() {
            const start = (this.currentPage - 1) * this.itemsPerPage;
            const end = start + this.itemsPerPage;
            return this.filteredItems.slice(start, end);
        }


    },
    methods: {

        processString(str) {
            if (this.data.area != null) {
                // Split the string by space
                const parts = str.split(" ");

                // Count 
                const count = parts.length;

                // Get data by splitting ;)
                const first = parts[count - 4];
                const second = parts[count - 3];
                const third = parts[count - 2];
                const fouth = parts[count - 1];

                return [count, first, second, third, fouth];
            }

        },

        truncateText(text, charLimit) {
            if (text.length > charLimit) {
                return text.slice(0, charLimit) + '...';
            }
            return text;
        },

        onPageChange(page) {
            this.currentPage = page;
        }
    }
};
</script>

<style>
.slide-in-enter-active {
    animation: slide-in-bck-br 0.5s;
}

@-webkit-keyframes slide-in-bck-br {
    0% {
        -webkit-transform: translateZ(700px) translateY(300px) translateX(400px);
        transform: translateZ(700px) translateY(300px) translateX(400px);
        opacity: 0;
    }

    100% {
        -webkit-transform: translateZ(0) translateY(0) translateX(0);
        transform: translateZ(0) translateY(0) translateX(0);
        opacity: 1;
    }
}

@keyframes slide-in-bck-br {
    0% {
        -webkit-transform: translateZ(700px) translateY(300px) translateX(400px);
        transform: translateZ(700px) translateY(300px) translateX(400px);
        opacity: 0;
    }

    100% {
        -webkit-transform: translateZ(0) translateY(0) translateX(0);
        transform: translateZ(0) translateY(0) translateX(0);
        opacity: 1;
    }
}
</style>