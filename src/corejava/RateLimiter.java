package corejava;
//Implement a rate-limiter algorithm (sliding window log) as a coding exercise.
public class RateLimiter {
    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter();
        // Test the rate limiter
        System.out.println(limiter.isAllowed("user1", 10, 60)); // true
        System.out.println(limiter.isAllowed("user1", 10, 60)); // true
        System.out.println(limiter.isAllowed("user1", 10, 60)); // true
    }
    // Implement the sliding window log algorithm
    public boolean isAllowed(String userId, int maxRequests, int windowSizeInSeconds)
    {
        // For simplicity, we will just return true for now
        // In a real implementation, you would keep track of the requests made by each user
        // and check if they are within the allowed limit
        return true;
    }   
}
