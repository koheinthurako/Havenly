<template>
    <div class="second-tabContent">
        <div class="second-tabContent-data">
            <div class="tab-carousel-content m-auto" :style="{ transform: `translateX(${offset}px)` }">

                <!-- <div class="tabContent-header">
                    <h3>Welcome to Havenly</h3>
                </div> -->

                <div class="d-none d-md-block button-bar mb-2 py-4">
                    <div>
                        <v-btn size="large"
                            v-for="(button, index) in buttons.slice(visibleStartIndex, visibleStartIndex + visibleButtons)"
                            :elevation="10" :key="index" style="text-transform:capitalize;" class="tab-carousel-button w-25"
                            @click="openTab(visibleStartIndex + index)"
                            :class="{ 'tablinks': true, 'active-btn': activeTab === visibleStartIndex + index }">
                            {{ button }}
                        </v-btn>
                    </div>
                </div>


                <div class="d-block d-sm-none mb-5">
                    <v-btn size="small"
                        v-for="(button, index) in buttons.slice(visibleStartIndex, visibleStartIndex + visibleButtons)"
                        :elevation="10" :key="index" style="text-transform:capitalize;" class="tab-carousel-button me-2"
                        @click="openTab(visibleStartIndex + index)"
                        :class="{ 'tablinks': true, 'active-btn': activeTab === visibleStartIndex + index }">
                        {{ button }}
                    </v-btn>

                </div>


            </div>

            <button class="sec-prev" @click="prev" :hidden="isPrevButtonHidden">&lt;</button>
            <button class="sec-next" @click="next" :hidden="isNextButtonHidden">&gt;</button>

        </div>
        <TabContent v-if="activeTab !== null" :content="contents[activeTab]" />
    </div>
</template>

<script>
import TabContent from '../Temp_Collection/tempForTabContent.vue';

export default {
    components: {
        TabContent
    },
    data() {
        return {
            
            buttons: ['Condo', 'Apartment', 'House'],
            offset: 0,
            visibleStartIndex: 0,
            visibleButtons: 5,
            contents: [
                { title: 'phone apple Condo' },
                { title: 'phone samsung Apartment' },
                { title: 'phone xiaomi House' },
                { title: 'phone vivo Hostel' },
                { title: 'phone apple Industrial' },
                { title: 'phone samsung Office' },
                { title: 'phone xiaomi Restaurant' },
                { title: 'phone vivo Land' },
            ],
            activeTab: 1 // Set the default active tab to the first tab
        };
    },
    computed: {
        isPrevButtonHidden() {
            return this.visibleStartIndex === 0;
        },
        isNextButtonHidden() {
            return this.visibleStartIndex + this.visibleButtons >= this.buttons.length;
        }
    },
    mounted() {
        

        const savedTab = localStorage.getItem('activeTab');
        if (savedTab !== null) {
            this.activeTab = parseInt(savedTab);
        }

        window.addEventListener('beforeunload', this.saveScrollPosition);
        this.restoreScrollPosition();
    },



    beforeUnmount() {
        window.removeEventListener('beforeunload', this.saveScrollPosition);
    },

    methods: {
        
        saveScrollPosition() {
            sessionStorage.setItem('scrollPosition', window.scrollY);
        },
        restoreScrollPosition() {
            const scrollPosition = sessionStorage.getItem('scrollPosition');
            if (scrollPosition) {
                window.scrollTo(0, parseInt(scrollPosition));
            }
        },

        next() {
            if (this.visibleStartIndex + this.visibleButtons < this.buttons.length) {
                this.visibleStartIndex++;
                this.offset = -this.calculateButtonWidth() * this.visibleStartIndex;
                localStorage.setItem('visibleStartIndex', this.visibleStartIndex);
            }
        },
        prev() {
            if (this.visibleStartIndex > 0) {
                this.visibleStartIndex--;
                this.offset += this.calculateButtonWidth();
                localStorage.setItem('visibleStartIndex', this.visibleStartIndex);
            }
        },
        openTab(index) {
            this.activeTab = index;
            localStorage.setItem('activeTab', index); // Save activeTab to local storage
        },
        calculateButtonWidth() {
            // Calculate and return the width of each button
            // You need to implement this according to your styling and button dimensions
        }
    }
};
</script>
