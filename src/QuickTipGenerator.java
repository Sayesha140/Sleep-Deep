import java.util.Random;

public class QuickTipGenerator implements ITipGenerator{

    String[] tips = {
            "Going to bed and waking up at the same time every day is a cornerstone of healthy sleep hygiene. Try to maintain a consistent bed routine!",
            "Did you know that caffeine stays in your system for hours? Avoid caffeine and heavy meals before bedtime.",
            "Create a relaxing bedtime routine. Activities like reading or meditating can help your body wind down.",
            "Short naps can refresh your mind and energy! Just do not nap too late, or it might affect your nighttime sleep!",
            "Exercise during the day can improve your sleep quality at night, but avoid intense workouts right before bed.",
            "Is your bedroom optimized for sleep? Keep it cool, dark, and quiet! Your body will thank you!",
            "End your day with a little mindfulness! Take deep breaths or try meditation to clear your mind and ease into sleep.",
            "Say goodbye to screens an hour before bed! The blue light messes with your circadian rhythm and makes it harder to fall asleep.",
            "Take a warm bath or shower before bed to relax your muscles and prepare your body for restful sleep.",
            "Expose yourself to natural sunlight during the day. It helps regulate your sleep-wake cycle and improves the quality of your sleep.",
            "Just 30 minutes of exercise daily can help you fall asleep quicker and sleep quality!",
            "A tidy room promotes better sleep! Spend a few minutes tidying up your space before bed for a peaceful, stress-free night.",
            "Soft lighting, a comfy mattress, and a clean room can turn your bedroom into the ultimate sleep sanctuary!",
            "Afternoon naps are great, but avoid napping too late. It can affect your ability to fall asleep at night.",
            "Your bed should be a haven for relaxation! Make it extra cozy tonight with fresh sheets and a comfy pillow.",
            "If you’re oversleeping in the mornings, it might be because you're staying up too late. A consistent bedtime will make your mornings easier and more energetic!",
            "If you did not get enough sleep last night, a power nap (10-20 minutes) during the day can refresh you without disrupting your nighttime sleep!",
            "Oversleeping may sound like a luxury, but it actually messes with your sleep cycle and can make you feel sluggish. Time to rise and shine!",
            "Aim for 7-9 hours of sleep. Too little leaves you sluggish, too much leaves you groggy! Find your sweet spot for the perfect amount of sleep."
    };


      public String generateTips() {
            return tips[new Random().nextInt(tips.length)];
        }
    }
