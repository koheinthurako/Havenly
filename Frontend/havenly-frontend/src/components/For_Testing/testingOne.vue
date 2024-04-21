<!-- <template>
    <div class="container">
        <div class="carousel">
            <div class="inner" ref="inner" :style="innerStyles">
                <v-img v-for="(data, index) in cards" :key="index" :src="data.url" class="card" />
            </div>
    
            
        </div>
        <v-btn @click="prev">Prev</v-btn>
            <v-btn @click="next">Next</v-btn>
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
                { url: require('@/assets/img/8.jpg') },
            ],
            innerStyles: {},
            step: '',
            transitioning: false
        };
    },

    mounted() {
        this.setStep();
        this.resetTranslate();
    },

    methods: {
        setStep() {
            const innerWidth = this.$refs.inner.offsetWidth; // Use offsetWidth instead of scrollWidth
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
            const listener = () => {
                callback();
                this.$refs.inner.removeEventListener('transitionend', listener);
            };
            this.$refs.inner.addEventListener('transitionend', listener);
        },

        resetTranslate() {
            this.innerStyles = {
                transition: 'none',
                transform: `translateX(-${this.step})`
            };
        }
    }
};
</script>

<style>
.carousel {
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-content: center;
    overflow: hidden;
}

.inner {
    transition: transform 0.2s;
    display: flex;
    justify-content: center;
    align-items: center;
}

.card {
    width: 200px;
    margin-right: 10px;
    border-radius: 4px;
}
</style> -->

<template>
    <div class="container">
        <div class="carousel" @mouseenter="stopAutoScroll" @mouseleave="startAutoScroll">
            <div class="inner" ref="inner" :style="innerStyles">
                <v-img v-for="(data, index) in cards" :key="index" :src="data.url" class="card-img" />
            </div>
        </div>

        <v-btn @click="prev">Prev</v-btn>
        <v-btn @click="next">Next</v-btn>
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
                { url: require('@/assets/img/8.jpg') },
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


        // afterTransition(callback) {
        //     const listener = () => {
        //         callback();
        //         this.$refs.inner.removeEventListener('transitionend', listener);
        //     };
        //     this.$refs.inner.addEventListener('transitionend', listener);
        // },

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
.container {
    position: relative;
}

.carousel {
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-content: center;
    overflow: hidden;
}

.inner {
    transition: transform 0.4s;
    display: flex;
    justify-content: center;
    align-items: center;
}

.card-img {
    width: 200px;
    margin-right: 10px;
    border-radius: 4px;
}
</style>