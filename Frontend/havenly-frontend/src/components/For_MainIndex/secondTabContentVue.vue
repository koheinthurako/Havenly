<template>
    <div class="second-tabContent">
        <div class="second-tabContent-data">
            <div class="tab-carousel-content m-auto my-2" :style="{ transform: `translateX(${offset}px)` }">

                <div class="tabContent-header">

                    <h3>Welcome to Havenly</h3>
                </div>

                <div class="d-none d-md-block button-bar ">
                    <div>
                        <v-btn size="large"
                            v-for="(button, index) in buttons.slice(visibleStartIndex, visibleStartIndex + visibleButtons)"
                            :elevation="20" :key="index" style="text-transform:capitalize;"
                            class="tab-carousel-button " @click="openTab(visibleStartIndex + index)"
                            :class="{ 'tablinks': true, 'active': activeTab === visibleStartIndex + index }">
                            {{ button }}
                            <div id="bar" :class="{ 'active-bar': activeTab === visibleStartIndex + index }"></div>
                        </v-btn>
                    </div>
                </div>


                <div class="d-block d-sm-none">
                    <v-btn size="small"
                        v-for="(button, index) in buttons.slice(visibleStartIndex, visibleStartIndex + visibleButtons)"
                        :elevation="20" :key="index" style="text-transform:capitalize;" class="tab-carousel-button me-2"
                        @click="openTab(visibleStartIndex + index)"
                        :class="{ 'tablinks': true, 'active': activeTab === visibleStartIndex + index }">
                        {{ button }}
                    </v-btn>

                </div>


            </div>

            <div class="pointer-button ms-2" :hidden="true">
                <v-btn class="prev" @click="prev" :disabled="isPrevButtonHidden">&lt;</v-btn>
                <v-btn class="next" @click="next" :disabled="isNextButtonHidden">&gt;</v-btn>
            </div>
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
            buttons: ['Condo', 'Apart', 'House', 'Hostel'],
            offset: 0,
            visibleStartIndex: 0,
            visibleButtons: 10,
            contents: [
                { title: 'phone apple Condo' },
                { title: 'phone samsung Apartment' },
                { title: 'phone xiaomi House' },
                { title: 'phone vivo Hostel' },
            ],
            activeTab: 0 // Set the default active tab to the first tab
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
        // Retrieve saved activeTab from local storage
        const savedTab = localStorage.getItem('activeTab');
        if (savedTab !== null) {
            this.activeTab = parseInt(savedTab); // Set activeTab to the saved value if it exists
        }


        // maintain scroll position
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
