<template>
    <div class="ads-medium-page">

        <div class="third-carousel-medium" @mouseenter="stopAutoScroll" @mouseleave="startAutoScroll">
            <div class="inner-medium" ref="inner" :style="innerStyles">
                <v-img v-for="(data, index) in cards" :key="index" :src="data.url" class="card-img-medium">
                    <div class="overlay"></div>
                </v-img>
            </div>
        </div>


        <v-btn @click="prev" class="medium-next"><v-icon>mdi-chevron-left</v-icon></v-btn>
        <v-btn @click="next" class="medium-prev"><v-icon>mdi-chevron-right</v-icon></v-btn>

    </div>
</template>

<script>
export default {
    data() {
        return {
            cards: [
                { url: require('@/assets/img/1.jpg') },
                { url: require('@/assets/img/2.jpg') },
                { url: require('@/assets/img/3.jpg') },
                { url: require('@/assets/img/4.jpg') },
                { url: require('@/assets/img/5.jpg') },
                { url: require('@/assets/img/6.jpg') },
                { url: require('@/assets/img/7.jpg') },


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


<style>
/*
.ads-medium-page {
    width: 100%;
    height: auto;
    position: relative;


    .third-carousel-medium {
        width: 100%;
        height: auto;

        overflow: hidden;
        display: flex;
        align-items: center;

        .inner-medium {
            display: flex;

            align-items: center;
            transition: transform 0.4s ease-in-out;
        }

        .card-img-medium {
            width: 390px;
            height: auto;
            border-radius: 6px;
            background-repeat: no-repeat;
            object-fit: cover;
            background-size: cover;
            display: flex;

            z-index: 300;

            .overlay {
                position: absolute;
                width: 100%;
                height: 100%;
                top: 0;
                left: 0;
                background-color: rgba(0, 0, 0, 0.5);
            }

        }

    }

    .medium-next,
    .medium-prev {
        position: absolute;
        width: 60px;
        height: 60px;
        border-radius: 50%;
        background-color: rgba(255, 255, 255, 0.6);
        backdrop-filter: blur(30px);
        -webkit-backdrop-filter: blur(30px);
        transition: all 0.4s ease-in-out;
        z-index: 500;
        top: 40%;

        .v-icon {
            font-size: 44px;
        }
    }

    .medium-next {
        left: 5%;
        opacity: 0;
        transform: translateX(-100px);
        transition: opacity 0.5s ease, transform 0.5s ease;
    }

    .medium-prev {
        right: 5%;
        opacity: 0;
        transform: translateX(100px);
        transition: opacity 0.5s ease, transform 0.5s ease;
    }

    &:hover .medium-next {
        opacity: 1;
        transform: translateX(0);
    }

    &:hover .medium-prev {
        opacity: 1;
        transform: translateX(0);
    }
}

@media only screen and (min-width: 768px) {

    
    .ads-medium-page {
        .card-img-medium {
            margin: 0px 8px;

        }
    }
}

@media only screen and (max-width: 767px) {

   
    .ads-medium-page {
        .card-img-medium {
            margin-right: 5px;
        }
    }
}</style>*/