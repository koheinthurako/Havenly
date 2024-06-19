<template>
    <div class="ads-sm-page">
        <div class="third-carousel-sm" @mouseenter="stopAutoScroll" @mouseleave="startAutoScroll">
            <div class="inner-sm" ref="inner" :style="innerStyles">
                <v-img v-for="(data, index) in cards" :key="index" :src="data.url" class="card-img-sm">
                    <div class="overlay"></div>
                </v-img>
            </div>
        </div>


        <v-btn @click="prev" class="sm-next"><v-icon>mdi-chevron-left</v-icon></v-btn>
        <v-btn @click="next" class="sm-prev"><v-icon>mdi-chevron-right</v-icon></v-btn>

    </div>
</template>

<script>
export default {
    data() {
        return {
            cards: [
                { url: require('@/assets/img/house-1.jpg') },
                { url: require('@/assets/img/house-2.jpg') },
                { url: require('@/assets/img/house-3.jpg') },
                { url: require('@/assets/img/house-4.jpg') },
                { url: require('@/assets/img/house-5.jpg') },
                { url: require('@/assets/img/house-6.jpg') },
                { url: require('@/assets/img/house-7.jpg') },


            ],
            innerStyles: {},
            step: '',
            transitioning: false,
            autoScrollInterval: null,
            autoScrollSpeed: 3000, // Adjust as needed
        };
    },

    mounted() {
        this.setStep();
        this.resetTranslate();
        this.startAutoScroll();
        localStorage.removeItem('openTab');
    },

    methods: {
        setStep() {
            const innerWidth = this.$refs.inner.offsetWidth;
            const totalCards = this.cards.length;
            this.step = `${innerWidth / totalCards}px`;
        },

        next() {
            if (this.transitioning) return;
            this.transitioning = true;
            this.moveLeft();
            this.afterTransition(() => {
                const card = this.cards.shift();
                this.cards.push(card);
                this.resetTranslate();
                this.transitioning = false;
            });
        },

        prev() {
            if (this.transitioning) return;
            this.transitioning = true;
            this.moveRight();
            this.afterTransition(() => {
                const card = this.cards.pop();
                this.cards.unshift(card);
                this.resetTranslate();
                this.transitioning = false;
            });
        },

        moveLeft() {
            this.innerStyles = {
                transform: `translateX(-${this.step}) translateX(-${this.step})`
            };
        },

        moveRight() {
            this.innerStyles = {
                transform: `translateX(${this.step}) translateX(-${this.step})`
            };
        },

        afterTransition(callback) {
            const innerRef = this.$refs.inner;
            if (!innerRef) return; // Check if innerRef is null
            const listener = () => {
                callback();
                innerRef.removeEventListener('transitionend', listener);
            };
            innerRef.addEventListener('transitionend', listener);
        },

        resetTranslate() {
            this.innerStyles = {
                transition: 'none',
                transform: `translateX(-${this.step})`
            };
        },

        startAutoScroll() {
            this.autoScrollInterval = setInterval(() => {
                this.next();
            }, this.autoScrollSpeed);
        },

        stopAutoScroll() {
            clearInterval(this.autoScrollInterval);
        }
    }
};
</script>
